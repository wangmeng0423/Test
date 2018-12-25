package duanxinCode.main;





import org.json.JSONObject;

import duanxinCode.main.httpclient.DefaultHTTPClient;
import duanxinCode.main.httpclient.HTTPClient;
import duanxinCode.main.httpclient.HTTPException;
import duanxinCode.main.httpclient.HTTPMethod;
import duanxinCode.main.httpclient.HTTPRequest;
import duanxinCode.main.httpclient.HTTPResponse;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;


public class SmsSingleSender extends SmsBase {

    private String url = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms";

    public SmsSingleSender(int appid, String appkey) {
        super(appid, appkey, new DefaultHTTPClient());
    }

    public SmsSingleSender(int appid, String appkey, HTTPClient httpclient) {
        super(appid, appkey, httpclient);
    }

    /**
     * ��ͨ����
     *
     * ��ͨ�������Žӿڣ���ȷָ�����ݣ�����ж��ǩ���������������ԡ����ķ�ʽ��ӵ���Ϣ�����У�����ϵͳ��ʹ��Ĭ��ǩ��
     *
     * @param type �������ͣ�0 Ϊ��ͨ���ţ�1 Ӫ������
     * @param nationCode �����룬�� 86 Ϊ�й�
     * @param phoneNumber ������������ֻ���
     * @param msg ��Ϣ���ݣ������������ģ���ʽһ�£����򽫷��ش���
     * @param extend ��չ�룬�����
     * @param ext �����ԭ�����صĲ����������
     * @return {@link}SmsSingleSenderResult
     * @throws HTTPException  http status exception
     * @throws JSONException  json parse exception
     * @throws IOException    network problem
     */
    public SmsSingleSenderResult send(int type, String nationCode, String phoneNumber,
        String msg, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        long random = SmsSenderUtil.getRandom();
        long now = SmsSenderUtil.getCurrentTime();
        JSONObject body = new JSONObject()
            .put("tel", (new JSONObject()).put("nationcode", nationCode).put("mobile", phoneNumber))
            .put("type", type)
            .put("msg", msg)
            .put("sig", SmsSenderUtil.calculateSignature(this.appkey, random, now, phoneNumber))
            .put("time", now)
            .put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
            .put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");

        HTTPRequest req = new HTTPRequest(HTTPMethod.POST, this.url)
            .addHeader("Conetent-Type", "application/json")
            .addQueryParameter("sdkappid", this.appid)
            .addQueryParameter("random", random)
            .setConnectionTimeout(60 * 1000)
            .setRequestTimeout(60 * 1000)
            .setBody(body.toString());

        // TODO Handle timeout exception
        try {
            // May throw IOException and URISyntaxexception
            HTTPResponse res = httpclient.fetch(req);

            // May throw HTTPException
            handleError(res);

            // May throw JSONException
            return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
        } catch(URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
    }

    /**
     * ָ��ģ�嵥��
     *
     * @param nationCode �����룬�� 86 Ϊ�й�
     * @param phoneNumber ������������ֻ���
     * @param templateId ��Ϣ����
     * @param params ģ������б���ģ�� {1}...{2}...{3}����ô��Ҫ����������
     * @param sign ǩ���������գ�ϵͳ��ʹ��Ĭ��ǩ��
     * @param extend ��չ�룬�����
     * @param ext �����ԭ�����صĲ����������
     * @return {@link}SmsSingleSenderResult
     * @throws HTTPException  http status exception
     * @throws JSONException  json parse exception
     * @throws IOException    network problem
     */
    public SmsSingleSenderResult sendWithParam(String nationCode, String phoneNumber, int templateId,
        ArrayList<String> params, String sign, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        long random = SmsSenderUtil.getRandom();
        long now = SmsSenderUtil.getCurrentTime();

        JSONObject body = new JSONObject()
            .put("tel", (new JSONObject()).put("nationcode", nationCode).put("mobile", phoneNumber))
            .put("sig", SmsSenderUtil.calculateSignature(appkey, random, now, phoneNumber))
            .put("tpl_id", templateId)
            .put("params", params)
            .put("sign", sign)
            .put("time", now)
            .put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
            .put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");

        HTTPRequest req = new HTTPRequest(HTTPMethod.POST, this.url)
            .addHeader("Conetent-Type", "application/json")
            .addQueryParameter("sdkappid", this.appid)
            .addQueryParameter("random", random)
            .setConnectionTimeout(60 * 1000)
            .setRequestTimeout(60 * 1000)
            .setBody(body.toString());

        try {
            // May throw IOException and URISyntaxexception
            HTTPResponse res = httpclient.fetch(req);

            // May throw HTTPException
            handleError(res);

            // May throw JSONException
            return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
        } catch(URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
    }

    public SmsSingleSenderResult sendWithParam(String nationCode, String phoneNumber, int templateId,
        String[] params, String sign, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        return sendWithParam(nationCode, phoneNumber, templateId,
            new ArrayList<String>(Arrays.asList(params)), sign, extend, ext);
    }
}

