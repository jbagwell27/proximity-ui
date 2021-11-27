package code.proximityui.data;

public class Parameters {

    private boolean use_official_art;
    private boolean reminder_text;
    private boolean artist_outline;
    private boolean copyright;
    private cardBorder border;

    //global only variables
    private boolean debug;
    private int threads;


    public Parameters() {
        use_official_art = false;
        reminder_text = false;
        artist_outline = false;
        copyright = true;
        border = cardBorder.black;
        threads = 10;
        debug = false;
    }

    public boolean isUse_official_art() {
        return use_official_art;
    }

    public void setUse_official_art(boolean use_official_art) {
        this.use_official_art = use_official_art;
    }

    public boolean isReminder_text() {
        return reminder_text;
    }

    public void setReminder_text(boolean reminder_text) {
        this.reminder_text = reminder_text;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public cardBorder getBorder() {
        return border;
    }

    public void setBorder(cardBorder border) {
        this.border = border;
    }

    public boolean isArtist_outline() {
        return artist_outline;
    }

    public void setArtist_outline(boolean artist_outline) {
        this.artist_outline = artist_outline;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public String toString() {
        String result = "--use_official_art=" + use_official_art +
                " --reminder_text=" + reminder_text +
                " --border=" + border.toString() +
//                " --artist_outline=" + artist_outline +
                " --copyright=" + copyright;
        if (debug) {
            result += " --debug=" + debug;
        }
        if (threads != 10) {
            result += " --threads=" + threads;
        }
        return result;
    }

    private enum cardBorder {black, none}


}
