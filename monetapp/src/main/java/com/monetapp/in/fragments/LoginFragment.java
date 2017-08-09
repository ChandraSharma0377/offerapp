package com.monetapp.in.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.dummy.R;
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.activities.MyApplication;
import com.monetapp.in.activities.SplashScreen;
import com.monetapp.in.asynctasks.AsyncProcessTemp;
import com.monetapp.in.utility.ShowAlertInformation;

import java.util.HashMap;

public class LoginFragment extends Fragment {

	private Button btnlogin;
	private EditText edtMobile,edtEmail;
	private LoginTask lat;
	private ProgressDialog progressDialog;

	public LoginFragment() {
		super();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

		View view = inflater.inflate(R.layout.lay_login, container, false);
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		btnlogin = (Button) view.findViewById(R.id.btnLogin);
		edtMobile = (EditText) view.findViewById(R.id.edtMobile);
		edtEmail = (EditText) view.findViewById(R.id.edtEmail);


		btnlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isValid()) {
					if (MainActivity.getNetworkHelper().isOnline()) {

						//HashMap<String, String> postDataParams = new HashMap<String, String>();
						// postDataParams.put("phone_number", "8879337402");
						//9699076104
						//	postDataParams.put("phone_number", mobile);

						//new LoginTask(postDataParams).execute(Commons.LOGIN_URL);

						MyApplication.setSharPreferancename("Guest User", edtEmail.getText().toString().trim(),
								edtMobile.getText().toString().trim(), true);
//						MainActivity.getMainScreenActivity().changeNavigationContentFragment(new HomeFragment(),
//								false);
//						MainActivity.getMainScreenActivity().setUserProfile();
//						MainActivity.getMainScreenActivity().setDrawerEnabled(true);
//						MainActivity.getMainScreenActivity().setLogoutEnabled(true);
						Intent i = new Intent(MainActivity.getMainScreenActivity(), SplashScreen.class);
						getActivity().startActivity(i);
						MainActivity.getMainScreenActivity().finish();
					} else {
						ShowAlertInformation.showNetworkDialog(getActivity());
					}
				}

			}
		});
		return view;
	}

	@Override
	public void onResume() {

		super.onResume();
		MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_login));


	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	private class LoginTask extends AsyncProcessTemp {

		public LoginTask(HashMap<String, String> postDataParams) {
			super(postDataParams);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(getActivity(), "", "login please wait...");
			progressDialog.setCancelable(true);
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setOnCancelListener(cancelListener);
		}

		@Override
		protected String doInBackground(String... params) {
			return super.doInBackground(params);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

//			if (200 == responseCode) {
//
//				String value = result.replace("\\", "");
//				if (value.length() > 2)
//					value = value.substring(1, value.length() - 1);
//				try {
//
//					// JSONArray jarray = new JSONArray(value);
//					JSONObject jo = new JSONObject(value);
//					// [{"status": "Success","User_ID": "2","UserName": "ashishs
//					// ","FirstName": "ashish","LastName": ""}]
//					//
//					String status = jo.getString("status");
//
//					if (status.equals("Success")) {
//						String User_ID = jo.getString("User_ID");
//						String FirstName = jo.getString("FirstName");
//						String LastName = jo.getString("LastName");
//						System.out.println(status + "\n" + User_ID + "\n" + FirstName + "\n" + LastName);
//						//delete old records
//						new DataHelperClass(getActivity()).clearRecord();
//						MainActivity.getMainScreenActivity().tv_name
//								.setText((FirstName + " " + LastName).toUpperCase());
//						MainActivity.getMainScreenActivity().setSharPreferancename(FirstName + " " + LastName, User_ID,
//								edtMobile.getText().toString().trim(), true);
//						MainActivity.getMainScreenActivity().changeNavigationContentFragment(new LandingFragment(),
//								false);
//
//					} else {
//						progressDialog.dismiss();
//						ShowAlertInformation.showDialog(getActivity(), "Error", "Invalid Mobile  Number");
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					ShowAlertInformation.showDialog(getActivity(), "Error", "No data found");
//					progressDialog.dismiss();
//				}
//				System.out.println("LoginTask result is : " + (result == null ? "" : result));
//				progressDialog.dismiss();
//			} else {
//				Log.i("LoginTask response", result == null ? "" : result);
//				ShowAlertInformation.showDialog(getActivity(), "Error", "Error");
//				progressDialog.dismiss();
//			}

		}

		OnCancelListener cancelListener = new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface arg0) {
				if (null != lat) {
					lat.cancel(true);
					System.out.println("refe" + lat.isCancelled());
					lat = null;
					// activity.getSupportFragmentManager().popBackStack();
				}
			}
		};
	}

	private   boolean isValidEmail(CharSequence target) {
		return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	}


	private boolean isValid(){
		boolean isvalid = true;
		String mobile = edtMobile.getText().toString().trim();
		String email = edtEmail.getText().toString().trim();
		edtMobile.setError(null);
		if (TextUtils.isEmpty(email)) {
			edtEmail.setError("Please enter email id");
			isvalid = false;
		}
		if (!TextUtils.isEmpty(email) && !isValidEmail(email)){
			edtEmail.setError("Please enter valid email id");
			isvalid = false;
		}
		if (TextUtils.isEmpty(mobile)) {
			edtMobile.setError("Please enter mobile no");
			isvalid = false;
		}
		if (!TextUtils.isEmpty(mobile) && mobile.length() < 10) {
			edtMobile.setError("Mobile no length should be 10");
			isvalid = false;
		}
		return  isvalid;
	}
}
