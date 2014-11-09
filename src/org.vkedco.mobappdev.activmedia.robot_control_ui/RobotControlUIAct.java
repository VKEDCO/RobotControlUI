package org.vkedco.mobappdev.activmedia.robot_control_ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RobotControlUIAct extends Activity {
	Button mBtnConnect;
	Button mBtnAccelerate;
	Button mBtnDecelerate;
	Button mBtnLeft;
	Button mBtnRight;
	Button mBtnStopServer;
	EditText tvRobotServerIP;
	PrintWriter mPrintWriterOut;
	Socket mSocket;
	int count = 0;
	JSONObject mJSONObj = new JSONObject();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBtnAccelerate = (Button) findViewById(R.id.btn_accelerate);
		mBtnDecelerate = (Button) findViewById(R.id.btn_decelerate);
		mBtnLeft = (Button) findViewById(R.id.btn_left);
		mBtnRight = (Button) findViewById(R.id.btn_right);
		mBtnStopServer = (Button) findViewById(R.id.btn_stop_server);
		mBtnConnect = (Button) findViewById(R.id.btn_connect);
		tvRobotServerIP = (EditText) findViewById(R.id.tvIPAddress);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);

		mBtnConnect.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				try {
					String input = tvRobotServerIP.getText().toString();
					mSocket = new Socket(input, 1222);
					mPrintWriterOut = new PrintWriter(
							mSocket.getOutputStream(), true);
					count = 1;
				} catch (UnknownHostException e1) {
					count = 0;
					Toast.makeText(getApplicationContext(), "Unknown Host",
							Toast.LENGTH_SHORT).show();
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					count = 0;
					Toast.makeText(getApplicationContext(), "Cannot connect",
							Toast.LENGTH_SHORT).show();

					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mBtnStopServer.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				try {
					mPrintWriterOut.println("-2");
					mSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(),
							"Cannot close the connection. Try again",
							Toast.LENGTH_SHORT).show();

					e.printStackTrace();
				}
			}
		});
		
		mBtnAccelerate.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (count == 0) {
					Toast.makeText(getApplicationContext(),
							"Enter IP address to connect", Toast.LENGTH_SHORT)
							.show();

				} else {
					try {
						mJSONObj.put("accelerate", "10");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					mPrintWriterOut.println(mJSONObj.toString());
					mJSONObj.remove("accelerate");
				}
			}
		});

		mBtnDecelerate.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (count == 0) {
					Toast.makeText(getApplicationContext(),
							"Enter IP address to connect", Toast.LENGTH_SHORT)
							.show();

				} else {
					try {
						mJSONObj.put("accelerate", "-10");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					mPrintWriterOut.println(mJSONObj.toString());
					mJSONObj.remove("accelerate");

				}

			}
		});

		mBtnRight.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (count == 0) {
					Toast.makeText(getApplicationContext(),
							"Enter IP address to connect", Toast.LENGTH_SHORT)
							.show();

				} else {
					try {
						mJSONObj.put("rotate", "-10");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					mPrintWriterOut.println(mJSONObj.toString());
					mJSONObj.remove("rotate");
				}

			}
		});

		mBtnLeft.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (count == 0) {
					Toast.makeText(getApplicationContext(),
							"Enter IP address to connect", Toast.LENGTH_SHORT)
							.show();

				} else {
					try {
						mJSONObj.put("rotate", "10");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					mPrintWriterOut.println(mJSONObj.toString());
					mJSONObj.remove("rotate");
				}
			}
		});

	}

}
