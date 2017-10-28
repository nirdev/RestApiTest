package com.company;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nir on 01/05/17.
 * This class provide all needed network operations synchronously
 */
public class NetworkAdmin implements INetworkAdmin {

    private static final String TAG = "NetworkAdmin";


    @Override
    public String getHttpResponse(String urlString) throws Exception {
//        InputStream is = null;
//        int length = 50000;
//
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(1000000 /* milliseconds */);
//            conn.setConnectTimeout(150000 /* milliseconds */);
//            conn.setRequestMethod("GET");
//            conn.setDoInput(true);
//            conn.connect();
//            int response = conn.getResponseCode();
//            System.out.println(TAG + "getHttpResponse: The response is: " + response);
//            is = conn.getInputStream();
//
//            // Convert the InputStream into a string
//            String contentAsString = convertInputStreamToString(is, length);
//            return contentAsString;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            throw new Exception("Could not perform HTTP GET request due to UnsupportedEncodingException" + e.getMessage());
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//            throw new Exception("Could not perform HTTP GET request due to ProtocolException" + e.getMessage());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            throw new Exception("Could not perform HTTP GET request due to MalformedURLException" + e.getMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new Exception("Could not perform HTTP GET request due to IOException" + e.getMessage());
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    throw new Exception("Could not close stdout" + e.getMessage());
//                }
//            }
//        }

        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    @Override
    public void getHttpResponseAsync(final String urlString, final IGetHttpResponseCallback callback) {
        new Thread(new Runnable() {
            public void run() {

                String httpResponse;
                try {
                    httpResponse = getHttpResponse(urlString);
                } catch (Exception e) {
                    System.out.println(TAG + "getHttpResponseAsync: Failed to retrieve value from getHttpResponse()"+ e);
                    httpResponse = null;
                }
                callback.onHttpResponded(httpResponse);

            }
        }).start();
    }

    @Override
    public int downloadFileFromUrl(String urlString, String pathToSave, final String fileName) throws Exception {
        isConnected();

        try {
            File rootFile = new File(pathToSave);
            rootFile.mkdir();
            URL url = new URL(urlString);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            FileOutputStream f = new FileOutputStream(new File(rootFile, fileName));
            InputStream in = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
        } catch (IOException e) {
            System.out.println(TAG + "Failed to download video: " + fileName + " from url: " + urlString + " to: " + pathToSave + e.toString());
            return RESULT_FAILED;
        }

        return RESULT_OK;
    }

    @Override
    public void downloadFileFromUrlAsync(final String urlString, final String pathToSave, final String fileName, final IDownloadFileCallback callback) {
        new Thread(new Runnable() {
            public void run() {

                int httpResponse;
                try {
                    httpResponse = downloadFileFromUrl(urlString,pathToSave,fileName);
                } catch (Exception e) {
                    System.out.println(TAG + "downloadFileFromUrlAsync: Failed to retrieve video result from downloadFileFromUrl()"+ e);
                    httpResponse = RESULT_FAILED;
                }
                callback.onDownloadFileFinished(httpResponse);

            }
        }).start();
    }


    private String convertInputStreamToString(InputStream stream, int length) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }

    // check device network connection
    private boolean isConnected() throws Exception {
       return true;
    }

//    public static synchronized String buildUrlWithParams(String url,String apiRequest, ArrayList<Pair<String,String>> listOfParameters){
//        Uri.Builder builder = new Uri.Builder()
//                .scheme("http")
//                .authority(url)
//                .path(apiRequest);
//
//        for (Pair<String, String> keyValuePair : listOfParameters) {
//            builder.appendQueryParameter(keyValuePair.first,keyValuePair.second);
//        }
//        return builder.build().toString();
//    }
}
