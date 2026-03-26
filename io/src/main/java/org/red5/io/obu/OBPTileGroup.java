package org.red5.io.obu;

/**
 * Parsed tile-group descriptor
 *
 * Contains tile-group boundaries and per-tile payload sizes as read from OBU tile-group syntax 
 *
 * @author mondain
 */
public class OBPTileGroup {

    /** Number of tiles carried by this tile-group unit*/
    public short numTiles;

    /** True when start and end tile indices are explicitly present in syntax*/
    public boolean tileStartAndEndPresentFlag;

    /**Start tile index of this tile group*/
    public short tgStart;

    /**End tile index of this tile group*/
    public short tgEnd;

    /**Byte size for each tile payload in decode order*/
    public long[] tileSize = new long[4096];
}
