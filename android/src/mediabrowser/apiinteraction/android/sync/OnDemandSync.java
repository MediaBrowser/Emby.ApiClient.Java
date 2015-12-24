package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

public class OnDemandSync {

    private Context context;

    public OnDemandSync(Context context) {
        this.context = context;
    }

    public void Run(SyncAccountInfo accountInfo) {

        if (!isAccountInfoValid(accountInfo)) {
            Log.d("OnDemandSync", "Error creating OnDemandSync: AccountInfo is incomplete");
            return;
        }

        AuthenticatorService.CreateSyncAccount(context, accountInfo);

        Account account = AuthenticatorService.GetAccount(accountInfo);

        // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        /*
         * Request the sync for the default account, authority, and
         * manual sync settings
         */
        ContentResolver.requestSync(account, accountInfo.authority, settingsBundle);
    }

    private boolean isAccountInfoValid(SyncAccountInfo accountInfo) {
        return accountInfo != null
                && !TextUtils.isEmpty(accountInfo.authority)
                && !TextUtils.isEmpty(accountInfo.accountName)
                && !TextUtils.isEmpty(accountInfo.accountType);
    }
}
