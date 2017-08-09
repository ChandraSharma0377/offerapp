package com.monetapp.in.asynctasks;

import android.os.AsyncTask;

import com.monetapp.in.beans.SmsBeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class AsyncProcess extends AsyncTask<String, Void, List<String>> {

	protected int responseCode = 0;
	private HashMap<String, String> postDataParams;
	private List<SmsBeans> smsBeansList;

	public AsyncProcess() {
	}

	public AsyncProcess(HashMap<String, String> postDataParams,List<SmsBeans> smsBeansList) {
		this.postDataParams = postDataParams;
		this.smsBeansList = smsBeansList;
	}

	@Override
	protected List<String> doInBackground(String... params) {
		String response = "";
		List<String> responseList = new ArrayList<>();
		int start = 0;
		int end = 0;
		int slab = 5;
		end =  smsBeansList.size()>slab ?slab:smsBeansList.size();
		for (int i = 0 ;i<end;i++) {
			URL url = null;
			HttpURLConnection conn = null;
			try {
				url = new URL(params[0].toString());
			} catch (MalformedURLException e2) {
				e2.printStackTrace();
			}
			try {
				conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(30000);
				conn.setConnectTimeout(30000);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				conn.setRequestProperty("charset", "utf-8");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				OutputStream os = conn.getOutputStream();
				responseCode = conn.getResponseCode();
				if (responseCode == HttpsURLConnection.HTTP_OK) {
					String line;
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					while ((line = br.readLine()) != null) {
						response += line;
					}
					responseList.add(response);
				} else {
					response = "";

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			start = end;
			end = (end + slab ) < smsBeansList.size() ? end+slab : smsBeansList.size();
		}
		return responseList;
	}

	private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		}

		return result.toString();
	}
}
