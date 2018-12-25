package duanxinCode.test;

import org.json.JSONException;

import duanxinCode.main.SmsSingleSender;
import duanxinCode.main.SmsSingleSenderResult;
import duanxinCode.main.httpclient.HTTPException;

import java.io.IOException;


public class QcloudSmsTest {
	
	 // ��Ҫ���Ͷ��ŵ��ֻ�����
    private static String phoneNumbers = null;

   public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

public static void sendMessage() {
	   // ����Ӧ��SDK AppID
       int appid = 1400110670; // 1400��ͷ

       // ����Ӧ��SDK AppKey
       String appkey = "fba519d8f32381222efa4370f2f6dfe1";

      

       // ����ģ��ID����Ҫ�ڶ���Ӧ��������
       // NOTE: �����ģ��ID`7839`ֻ��һ��ʾ����
       // ��ʵ��ģ��ID��Ҫ�ڶ��ſ���̨������
       int templateId = 155003;

       // ǩ��
       // NOTE: �����ǩ��"��Ѷ��"ֻ��һ��ʾ����
       // ��ʵ��ǩ����Ҫ�ڶ��ſ���̨�����룬����
       // ǩ������ʹ�õ���`ǩ������`��������`ǩ��ID`
       String smsSign = "һ���¶���ITС��";

       // ��������
       try {
           SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
           SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers,
               "��һ���¶���ITС�š�7845Ϊ���ĵ�¼��֤�룬����5��������д����Ǳ��˲���������Ա����š�", "", "");
           System.out.print(result);
       } catch (HTTPException e) {
           // HTTP��Ӧ�����
           e.printStackTrace();
       } catch (JSONException e) {
           // json��������
           e.printStackTrace();
       } catch (IOException e) {
           // ����IO����
           e.printStackTrace();
       }
   }
    
    
    
}
