package code.proximityui.data;

public class Parameters {

    private boolean use_official_art = false;
    private boolean reminder_text = false;
    private boolean debug = false;
    private int threads = 10;
    private String border = "black";
    private boolean artist_outline = false;
    private boolean copyright = true;

    public Parameters() {
    }

    public Parameters(boolean use_official_art, boolean reminder_text, boolean debug, int threads, String border, boolean artist_outline, boolean copyright) {
        this.use_official_art = use_official_art;
        this.reminder_text = reminder_text;
        this.debug = debug;
        this.threads = threads;
        this.border = border;
        this.artist_outline = artist_outline;
        this.copyright = copyright;
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

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
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
}
