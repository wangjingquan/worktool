
public class Des3Decode {

  public static void main(String[] args) throws Exception {
    String result = "KVybJw1VSWAnh+dmQfxZ9g==";
    String deskey = "D046E6B6A4A85EB6C44C73372A0D5DF1AE76405173B3D5EC";
    String hexiv = "70706C6976656F6B";
    byte[] byteIv = DESUtil.hex2byte(hexiv);
    String ss = CryptogramHelper.Decrypt(result, deskey, byteIv);
    System.out.println(ss);
  }
}
