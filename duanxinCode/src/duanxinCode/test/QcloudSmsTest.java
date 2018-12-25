package duanxinCode.test;

import org.json.JSONException;

import duanxinCode.main.SmsSingleSender;
import duanxinCode.main.SmsSingleSenderResult;
import duanxinCode.main.httpclient.HTTPException;

import java.io.IOException;


public class QcloudSmsTest {
	
	 // 需要发送短信的手机号码
    private static String phoneNumbers = null;

   public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

public static void sendMessage() {
	   // 短信应用SDK AppID
       int appid = 1400110670; // 1400开头

       // 短信应用SDK AppKey
       String appkey = "fba519d8f32381222efa4370f2f6dfe1";

      

       // 短信模板ID，需要在短信应用中申请
       // NOTE: 这里的模板ID`7839`只是一个示例，
       // 真实的模板ID需要在短信控制台中申请
       int templateId = 155003;

       // 签名
       // NOTE: 这里的签名"腾讯云"只是一个示例，
       // 真实的签名需要在短信控制台中申请，另外
       // 签名参数使用的是`签名内容`，而不是`签名ID`
       String smsSign = "一个懵懂的IT小张";

       // 单发短信
       try {
           SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
           SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers,
               "【一个懵懂的IT小张】7845为您的登录验证码，请于5分钟内填写。如非本人操作，请忽略本短信。", "", "");
           System.out.print(result);
       } catch (HTTPException e) {
           // HTTP响应码错误
           e.printStackTrace();
       } catch (JSONException e) {
           // json解析错误
           e.printStackTrace();
       } catch (IOException e) {
           // 网络IO错误
           e.printStackTrace();
       }
   }
    
    
    
}
