package org.red5.io.obu;

/**
 * Parsed frame header model used
 *
 * This structure stores all frame-level syntax elements extracted from a frame
 * header OBU
 *
 * @author mondain
 */
public class OBPFrameHeader {

    /** Reuse an already decoded reference frame for display */
    public boolean showExistingFrame;

    /** Index in the reference frame map when showExistingFrame is set */
    public byte frameToShowMapIdx;

    /** Optional temporal point timing information */
    public TemporalPointInfo temporalPointInfo;

    /** Display frame id when frame id signaling is enabled */
    public long displayFrameId;

    /** Frame coding type (key, inter, intra-only, switch) */
    public OBPFrameType frameType;

    /** Whether this frame is displayed */
    public boolean showFrame;

    /** Whether this frame can be shown by future showExistingFrame */
    public boolean showableFrame;

    /** Enables independent decoding resilience for this frame */
    public boolean errorResilientMode;

    /** Disables CDF adaptation for this frame */
    public boolean disableCdfUpdate;

    /** Allows screen-content coding tools for this frame */
    public boolean allowScreenContentTools;

    /** Forces integer motion vectors when enabled */
    public boolean forceIntegerMv;

    /** Current frame id value */
    public int currentFrameId;

    /** Allows frame dimensions to differ from sequence defaults */
    public boolean frameSizeOverrideFlag;

    /** Order hint for reference management and display order */
    public byte orderHint;

    /** Primary reference frame index used for context initialization */
    public byte primaryRefFrame;

    /** Signals presence of decoder buffer removal timing fields */
    public boolean bufferRemovalTimePresentFlag;

    /** Buffer removal timestamps per operating point */
    public long[] bufferRemovalTime = new long[32];

    /** Bitmask indicating which reference slots are refreshed by this frame */
    public byte refreshFrameFlags;

    /** Cached order hint per reference frame slot */
    public byte[] refOrderHint = new byte[8];

    /** Coded frame width minus 1 */
    public int frameWidthMinus1;

    /** Coded frame height minus 1 */
    public int frameHeightMinus1;

    /** Coded frame width in pixels */
    public int frameWidth;

    /** Coded frame height in pixels */
    public int frameHeight;

    /** Frame width before super-resolution downscaling */
    public int upscaledWidth;

    /** True when render size differs from coded frame size */
    public boolean renderAndFrameSizeDifferent;

    /** Render width minus 1 */
    public int renderWidthMinus1;

    /** Render height minus 1 */
    public int renderHeightMinus1;

    /** Render width in pixels */
    public int renderWidth;

    /** Render height in pixels */
    public int renderHeight;

    /** Super-resolution configuration for this frame */
    public SuperresParams superresParams = new SuperresParams();

    /** Allows Intra Block Copy mode */
    public boolean allowIntrabc;

    /** Uses short signaling for two key references */
    public boolean frameRefsShortSignaling;

    /** Last-frame index when short signaling is used */
    public byte lastFrameIdx;

    /** Golden-frame index when short signaling is used */
    public byte goldFrameIdx;

    /** Reference frame slot indices used by this frame */
    public byte[] refFrameIdx = new byte[OBPConstants.REFS_PER_FRAME];

    /** Delta frame id minus 1 for each inter reference */
    public byte[] deltaFrameIdMinus1 = new byte[7];

    /** Indicates that a valid reference frame has been found */
    public boolean foundRef;

    /** Allows high precision motion vectors */
    public boolean allowHighPrecisionMv;

    /** Interpolation filter signaling for inter prediction */
    public InterpolationFilter interpolationFilter = new InterpolationFilter();

    /** Allows per-block motion mode switching */
    public boolean isMotionModeSwitchable;

    /** Enables motion field projection from reference frames */
    public boolean useRefFrameMvs;

    /** Disables CDF updates at frame end */
    public boolean disableFrameEndUpdateCdf;

    /** Tile layout and tile group indexing information */
    public TileInfo tileInfo;

    /** Quantization parameters for luma/chroma and optional matrices */
    public QuantizationParams quantizationParams;

    /** Segmentation parameters and per-segment features */
    public SegmentationParams segmentationParams;

    /** Delta-Q signaling parameters */
    public DeltaQParams deltaQParams;

    /** Delta loop-filter signaling parameters */
    public DeltaLfParams deltaLfParams;

    /** Loop filter levels and delta update state */
    public LoopFilterParams loopFilterParams;

    /** CDEF filter strengths and damping configuration */
    public CdefParams cdefParams;

    /** Loop restoration filter configuration */
    public LrParams lrParams;

    /** Signals whether skip mode is available */
    public boolean skipModePresent;

    /** Enables reference frame selection signaling */
    public boolean referenceSelect;

    /** Allows warped motion mode */
    public boolean allowWarpedMotion;

    /** Uses reduced transform set */
    public boolean reducedTxSet;

    /** Global motion model parameters per reference frame */
    public GlobalMotionParams globalMotionParams;

    /** Film grain synthesis parameters for output reconstruction */
    public OBPFilmGrainParameters filmGrainParams;

    /** Frame size in 4x4 mode-info units */
    public int miCols, miRows;

    /** Per-frame lossless flags: coded-lossless and all-lossless */
    public boolean codedLossless, allLossless;

    /** Internal helper flag for inter reference selection path */
    public boolean referenceSelectInter;

    /** True when transform mode can be selected in blocks */
    public boolean txModeSelect;

    /** Transform mode used for this frame */
    public OBPTxMode txMode;

    /** Tile layout and indexing syntax fields */
    public static class TileInfo {

        /** Uniform tile spacing flag from frame header syntax */
        public boolean uniformTileSpacingFlag;

        /** Number of tile columns */
        public int tileCols;

        /** Number of tile rows */
        public int tileRows;

        /** log2(tileCols) when applicable */
        public int tileColsLog2;

        /** log2(tileRows) when applicable */
        public int tileRowsLog2;

        /** Tile id used for context update */
        public int contextUpdateTileId;

        /** Number of bytes used to encode tile size minus 1 */
        public int tileSizeBytesMinus1;
    }

    /** Temporal point information associated with frame timing model */
    public static class TemporalPointInfo {

        /** Frame presentation timestamp value */
        public long framePresentationTime;
    }

    /** Super-resolution parameters controlling coded-to-display scaling */
    public static class SuperresParams {

        /** Enables super-resolution for this frame */
        public boolean useSuperres;

        /** Coded denominator syntax value */
        public byte codedDenom;

        /** Effective super-resolution denominator */
        public int superresDenom;
    }

    /** Interpolation filter mode signaling container */
    public static class InterpolationFilter {

        /** True when filter type may vary at block level */
        public boolean isFilterSwitchable;

        /** Selected interpolation filter when not switchable */
        public OBPInterpolationFilter interpolationFilter;
    }

    /** Quantization and quantization-matrix parameters */
    public static class QuantizationParams {

        /** Base Q index */
        public int baseQIdx;

        /** Luma DC delta Q */
        public int deltaQYDc;

        /** True when U/V delta Q values differ */
        public boolean diffUvDelta;

        /** U plane DC delta Q */
        public int deltaQUDc;

        /** U plane AC delta Q */
        public int deltaQUAc;

        /** V plane DC delta Q */
        public int deltaQVDc;

        /** V plane AC delta Q */
        public int deltaQVAc;

        /** Enables quantization matrices */
        public boolean usingQmatrix;

        /** Luma quantization matrix index */
        public int qmY;

        /** Chroma U quantization matrix index */
        public int qmU;

        /** Chroma V quantization matrix index */
        public int qmV;
    }

    /** Segmentation flags and per-segment feature tables */
    public static class SegmentationParams {

        /** Enables segmentation for this frame */
        public boolean segmentationEnabled;

        /** Signals segmentation map update */
        public boolean segmentationUpdateMap;

        /** Signals temporal prediction for segmentation map */
        public boolean segmentationTemporalUpdate;

        /** Signals segmentation data update */
        public boolean segmentationUpdateData;

        /** Feature enable flags indexed by segment and feature id */
        public boolean[][] featureEnabled;

        /** Feature data values indexed by segment and feature id */
        public short[][] featureData;

        public SegmentationParams() {
            featureEnabled = new boolean[8][8];
            featureData = new short[8][8];
        }
    }

    /** Delta quantizer signaling parameters */
    public static class DeltaQParams {

        /** Enables delta-Q signaling */
        public boolean deltaQPresent;

        /** Delta-Q resolution */
        public byte deltaQRes;
    }

    /** Delta loop-filter signaling parameters */
    public static class DeltaLfParams {

        /** Enables delta loop-filter signaling */
        public boolean deltaLfPresent;

        /** Delta loop-filter resolution */
        public byte deltaLfRes;

        /** Enables multi-plane delta loop-filter signaling */
        public boolean deltaLfMulti;
    }

    /** Loop-filter levels and update deltas */
    public static class LoopFilterParams {

        /** Loop-filter levels for frame planes/modes */
        public byte[] loopFilterLevel = new byte[4];

        /** Loop-filter sharpness level. */
        public byte loopFilterSharpness;

        /** Enables loop-filter reference/mode deltas */
        public boolean loopFilterDeltaEnabled;

        /** Signals update of loop-filter deltas */
        public boolean loopFilterDeltaUpdate;

        /** Update flags for reference deltas */
        public boolean[] updateRefDelta = new boolean[8];

        /** Loop-filter deltas per reference frame */
        public byte[] loopFilterRefDeltas = new byte[8];

        /** Update flags for mode deltas */
        public boolean[] updateModeDelta = new boolean[8];

        /** Loop-filter deltas per prediction mode */
        public byte[] loopFilterModeDeltas = new byte[8];
    }

    /** CDEF filtering parameters */
    public static class CdefParams {

        /** CDEF damping value minus 3 */
        public byte cdefDampingMinus3;

        /** Number of CDEF strength bits */
        public byte cdefBits;

        /** Primary CDEF strength for luma */
        public byte[] cdefYPriStrength = new byte[8];

        /** Secondary CDEF strength for luma */
        public byte[] cdefYSecStrength = new byte[8];

        /** Primary CDEF strength for chroma */
        public byte[] cdefUvPriStrength = new byte[8];

        /** Secondary CDEF strength for chroma */
        public byte[] cdefUvSecStrength = new byte[8];
    }

    /** Loop-restoration parameters */
    public static class LrParams {

        /** Restoration filter type for Y/U/V planes */
        public byte[] lrType = new byte[3];

        /** Restoration unit size shift */
        public byte lrUnitShift;

        /** Chroma restoration unit shift flag */
        public boolean lrUvShift;
    }

    /** Global motion model state per reference frame */
    public static class GlobalMotionParams {

        /** Global motion type per reference frame */
        public byte[] gmType = new byte[8];

        /** Current global motion parameters per reference frame */
        public int[][] gmParams = new int[8][6];

        /** Previous global motion parameters per reference frame */
        public int[][] prevGmParams = new int[8][6];
    }

}
