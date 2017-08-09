package com.monetapp.in.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.dummy.R;
import com.monetapp.in.activities.MainActivity;


public class ShowAlertInformation {

	public static void showDialog(Context context, String Title, String Message) {
		try {
			if (null != context) {
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
		}catch (Exception e){
			e.printStackTrace();
		}
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
