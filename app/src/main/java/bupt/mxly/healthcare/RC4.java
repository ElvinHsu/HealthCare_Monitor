package bupt.mxly.healthcare;

public class RC4 {
    final int len=256;
    public char[] sbox;
    public char[] key;//1~256位均可
    public char[] tempSbox;
    public char[] tempKey;//256位密钥


    public RC4()
    {
        sbox=new char[len];
        tempSbox=new char[len];
        key=new char[len];//1~256位均可
        tempKey=new char[len];//256位密钥
    }

    public void generateRandomKey()
    {
        //生成密钥
        for (int i = 0; i < key.length; i++) {
            key[i]=(char) (int)(Math.random()*1000);
        }

        initSbox();
    }

    public void generateCustomKey(char[] customkey)
    {
        for(int i=0;i<key.length;i++){
            key[i]=customkey[i%customkey.length];
        }
        initSbox();
    }

    public void initSbox()
    {
        //初始化sbox & tempKey
        for (int i = 0; i < sbox.length; i++) {
            sbox[i]=(char)i;
            tempKey[i]=key[i%key.length];
        }

        //初始化sbox排列
        int j=0;
        for (int i = 0; i < sbox.length; i++) {
            j=(sbox[i]+tempKey[i]+j)%len;

            //swap
            char temp=sbox[i];
            sbox[i]=sbox[j];
            sbox[j]=temp;
        }
    }

    public String crypt(String input)
    {
        String output="";

        //获得 sbox 副本
        for (int i = 0; i < sbox.length; i++) {
            tempSbox[i]=sbox[i];
        }

        if(input=="")
        {
            System.out.println("null input!");
        }
        else
        {
            int i=0,j=0;
            for (int k = 0; k < input.length(); k++) {
                i=(i+1)%len;
                j=(j+tempSbox[i])%len;

                //swap
                char temp=tempSbox[i];
                tempSbox[i]=tempSbox[j];
                tempSbox[j]=temp;

                int tempIndex=(tempSbox[i]+tempSbox[j])%len;
                output+=(char)(input.charAt(k)^tempSbox[tempIndex]);
            }
        }
        return output;
    }
}
