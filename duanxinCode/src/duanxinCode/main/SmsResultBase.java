package duanxinCode.main;



import org.json.JSONObject;

import duanxinCode.main.httpclient.HTTPResponse;

import org.json.JSONException;


public abstract class SmsResultBase {

    protected HTTPResponse response;

    /**
     * Parse result from HTTPResponse
     *
     * @param response  HTTP response from api return
     * @return SmsResultbase
     * @throws JSONException  json parse exception
     */
    public abstract SmsResultBase parseFromHTTPResponse(HTTPResponse response)
        throws JSONException;

    /**
     * Parse HTTP response to JSONObject
     *
     * @param response  HTTP response
     * @return JSONObject
     * @throws JSONException  json parse exception
     */
    public JSONObject parseToJson(HTTPResponse response) throws JSONException {
        // Set raw response
        this.response = response;
        // May throw JSONException
        return new JSONObject(response.body);
    }

    /**
     * Get raw response
     *
     * @return HTTPResponse
     */
    public HTTPResponse getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return response.body;
    }
}
