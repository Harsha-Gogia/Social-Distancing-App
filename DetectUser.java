//package com.codeWithHarsha.distiinceapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class DetectUser extends AppCompatActivity {
//
//    private final static String TAG = DetectUser.class.getSimpleName();
//    public static final int REQUEST_ENABLE_BT = 1;
//
//    private HashMap<String, BTLE_Device> mBTDevicesHashMap;
//    private ArrayList<BTLE_Device> mBTDevicesArrayList;
//    private ListAdapter_BTLE_Devices adapter;
//
//    private BroadcastReceiver_BTState mBTStateUpdateReceiver;
//    private Scanner_BTLE mBTLeScanner;
//
//    Button button;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detect_user);
//
//        button= findViewById(R.id.button4);
//        onStart();
//
//        button.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//                onPause();
//              switch (v.getId()) {
//
//                  case R.id.button4:
//                      Utils.toast(getApplicationContext(), "Pause Detect Button Pressed");
//                      break;
//                  default:
//                      break;
//              }
//                goToActiveUsersPage();
//          }
//      });
//        mBTStateUpdateReceiver = new BroadcastReceiver_BTState(getApplicationContext());
////        mBTLeScanner = new Scanner_BTLE(this, 7500, -75);
//
//        mBTDevicesHashMap = new HashMap<>();
//        mBTDevicesArrayList = new ArrayList<>();
//
//        adapter = new ListAdapter_BTLE_Devices(this, R.layout.activity_detect_user, mBTDevicesArrayList);
//    }
//    public void onBackPressed(){
////            super.onBackPressed();
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater= getMenuInflater();
//        inflater.inflate(R.menu.option_menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.settings_menu:
//                Intent intent3=new Intent(this, SettingsPage.class);
//                startActivity(intent3);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void goToActiveUsersPage() {
//        Intent intent2 = new Intent(this, ActiveUsers.class);
//        startActivity(intent2);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        registerReceiver(mBTStateUpdateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopScan();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unregisterReceiver(mBTStateUpdateReceiver);
//        stopScan();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // Check which request we're responding to
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_ENABLE_BT) {
//            // Make sure the request was successful
//            if (resultCode == RESULT_OK) {
//                Utils.toast(getApplicationContext(), "Bluetooth Turned On");
//            } else if (resultCode == RESULT_CANCELED) {
//                Utils.toast(getApplicationContext(), "Please turn on Bluetooth");
//            }
//        }
//    }
//    /**
//     * Adds a device to the ArrayList and Hashmap that the ListAdapter is keeping track of.
//     * @param device the BluetoothDevice to be added
//     * @param rssi the rssi of the BluetoothDevice
//     */
//    public void addDevice(BluetoothDevice device, int rssi) {
//        String address = device.getAddress();
//        if (!mBTDevicesHashMap.containsKey(address)) {
//            BTLE_Device btleDevice = new BTLE_Device(device);
//            btleDevice.setRSSI(rssi);
//
//            mBTDevicesHashMap.put(address, btleDevice);
//            mBTDevicesArrayList.add(btleDevice);
//        }
//        else {
//            mBTDevicesHashMap.get(address).setRSSI(rssi);
//        }
//        adapter.notifyDataSetChanged();
//    }
//    /**
//     * Clears the ArrayList and Hashmap the ListAdapter is keeping track of.
//     * Starts Scanner_BTLE.
//     * Changes the scan button text.
//     */
//    public void startScan(){
//        mBTDevicesArrayList.clear();
//        mBTDevicesHashMap.clear();
//
//        adapter.notifyDataSetChanged();
//
//        mBTLeScanner.start();
//    }
//    /**
//     * Stops Scanner_BTLE
//     * Changes the scan button text.
//     */
//    public void stopScan() {
//        mBTLeScanner.stop();
//    }
//}