
package com.company;


/**
 * Created by nir on 01/05/17.
 */
public interface INetworkAdmin {

    int RESULT_OK = 1;
    int RESULT_FAILED = -1;

    /**
     * Send request to new video from server
     * @param urlString to get http response from
     * @return http response
     * @throws Exception - anything that goes wrong should be passed via this exception
     */
    String getHttpResponse(String urlString) throws Exception;

    interface IGetHttpResponseCallback {
        void onHttpResponded(String result);
    }
    /**
     * Async version of "getHttpResponse"
     */
    void getHttpResponseAsync(String urlString, IGetHttpResponseCallback callback) throws Exception;



    /**
     * Download video from specified url
     * @param urlString url to download from
     * @param pathToSave path so tave the file in
     * @throws Exception - anything that goes wrong should be passed via this exception
     */
    int downloadFileFromUrl(String urlString, String pathToSave, String fileName) throws Exception;

    interface IDownloadFileCallback {
        void onDownloadFileFinished(int result);
    }
    /**
     * Async version of "downloadFileFromUrl"
     */
    void downloadFileFromUrlAsync(String url, String pathToSave, String fileName, IDownloadFileCallback callback) throws Exception;




}
