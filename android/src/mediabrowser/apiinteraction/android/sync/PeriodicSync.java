package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

public class PeriodicSync {

    private Context context;

    public PeriodicSync(Context context) {
        this.context = context;
    }

    public void Create(long syncIntervalSeconds, SyncAccountInfo accountInfo) {

        if (!isAccountInfoValid(accountInfo)) {
            Log.d("PeriodicSync", "Error creating PeriodicSync: AccountInfo is incomplete");
            return;
        }

        AuthenticatorService.CreateSyncAccount(context, accountInfo);

        Account account = AuthenticatorService.GetAccount(accountInfo);

        // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();

        /*
         * Turn on periodic syncing
         */
        ContentResolver.addPeriodicSync(
                account,
                accountInfo.authority,
                settingsBundle,
                syncIntervalSeconds);

    }

    public void Create(SyncAccountInfo accountInfo) {

        // Default to hourly
        Create(3600, accountInfo);
    }

    private boolean isAccountInfoValid(SyncAccountInfo accountInfo) {
        return accountInfo != null
                && !TextUtils.isEmpty(accountInfo.authority)
                && !TextUtils.isEmpty(accountInfo.accountName)
                && !TextUtils.isEmpty(accountInfo.accountType);
    }
}
