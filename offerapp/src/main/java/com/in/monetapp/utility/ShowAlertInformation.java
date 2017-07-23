package com.in.monetapp.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.dummy.R;
import com.in.monetapp.activities.MainActivity;


public class ShowAlertInformation {

	public static void showDialog(Context context, String Title, String Message) {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				context);

		alertDialog.setTitle(Title);

		alertDialog.setMessage(Message);

		alertDialog.setIcon(R.drawable.icon_launcher);

		alertDialog.setNegativeButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
					}
				});

		alertDialog.show();
	}

	public static void showDialogAndFinish(Context context, String Title, String Message) {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				context);

		alertDialog.setTitle(Title);

		alertDialog.setMessage(Message);

		alertDialog.setIcon(R.drawable.icon_launcher);

		alertDialog.setNegativeButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						MainActivity.getMainScreenActivity().onBackPressed();
					}
				});

		alertDialog.show();
	}

	public static void showNetworkDialog(Context context){
		ShowAlertInformation.showDialog(context, "Device offline", context.getString(R.string.offline));
	}
}
