#!/usr/bin/env bash
set -euo pipefail

root_dir="$(cd "$(dirname "$0")/.." && pwd)"
cd "$root_dir"

make_awk_prog_file() {
  local prog_file
  prog_file="$(mktemp)"
  cat > "$prog_file" <<'AWK'
BEGIN{c=0; inBlock=0}
{
  if (FNR==1) { inBlock=0 }
  if (inBlock) {
    c++;
    if ($0 ~ /\*\//) inBlock=0;
    next
  }
  if ($0 ~ /^\s*\/\//) { c++; next }
  if ($0 ~ /\/\*/) {
    c++;
    if ($0 !~ /\*\//) inBlock=1;
    next
  }
}
END{print c}
AWK
  echo "$prog_file"
}

count_for_paths() {
  # Accept file list via stdin
  mapfile -t files
  if [ ${#files[@]} -eq 0 ]; then echo 0; return; fi
  local prog_file
  prog_file=$(make_awk_prog_file)
  awk -f "$prog_file" "${files[@]}"
  rm -f "$prog_file"
}

list_java_files() {
  find "$1" -type f -path "*/src/*/java/*.java" -print0 | xargs -0 -I{} echo {}
}

total_files=$(list_java_files .)
total_lines=$(echo "$total_files" | wc -l | awk '{print $1}')
total_comments=$(echo "$total_files" | count_for_paths)
echo "TOTAL comments=$total_comments files=$total_lines"

for m in io common server service servlet client tests; do
  if [ -d "$m/src" ]; then
    files=$(list_java_files "$m")
    if [ -n "$files" ]; then
      module_files_count=$(echo "$files" | wc -l | awk '{print $1}')
      module_comments=$(echo "$files" | count_for_paths)
      module_total_lines=$(echo "$files" | xargs wc -l | awk '{s+=$1} END{print s+0}')
      printf "%s comments=%s files=%s totalLines=%s\n" "$m" "$module_comments" "$module_files_count" "$module_total_lines"
    fi
  fi
done
