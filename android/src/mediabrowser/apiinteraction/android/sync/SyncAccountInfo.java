package mediabrowser.apiinteraction.android.sync;

/**
 * Created by Mark on 2015-12-24.
 */
public class SyncAccountInfo {
    // The authority for the sync adapter's content provider
    public String authority = "emby.media";
    // An account type, in the form of a domain name
    public String accountType = "emby.media";
    // The account name
    public String accountName = "sync";

    public SyncAccountInfo(String theAuthority, String theAccountType, String theAccountName) {
        authority = theAuthority;
        accountType = theAccountType;
        accountName = theAccountName;
    }
}
