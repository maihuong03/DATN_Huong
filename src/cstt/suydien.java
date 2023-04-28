/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cstt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author HCB
 */
public class suydien {

    String factsbase;//Gia thiet
    int soluat = 0;
    int sosk = 0;
    String[] tapkl;
    String[] tapskt;
    String[] tapsk;
    String huyluat = "";//chua cac luat bi huy trong suy dien tien
    String vet = ""; //Chứa các luật trong quá trình sd, dùng để inkq
    ArrayList rulesbase = new ArrayList();//khai báo biến để chứa tập luật
    ArrayList facts = new ArrayList();//Khai báo biến để chứa tập sự kiện

    public suydien() {

        soluat = FileCount("src\\data\\dulieuluat.dat");
        sosk = FileCount("src\\data\\sukien.dat");
        loadtapkl();
        loadsk();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\data\\dulieuluat.dat"))) {

            String sCurrentLine;
            int i = 0;
            tapkl = new String[soluat];
            while ((sCurrentLine = br.readLine()) != null)//trong khi chưa kết thúc file
            {
                String[] ss = sCurrentLine.split("[|]");
                Rule dt = new Rule(i, ss[0], ss[1]);
                rulesbase.add(dt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadtapkl() {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\data\\dulieuluat.dat"))) {

            String sCurrentLine;
            int i = 0;
            tapkl = new String[soluat];
            while ((sCurrentLine = br.readLine()) != null)//trong khi chưa kết thúc file
            {
                String[] ss = sCurrentLine.split("[|]");
//                Rule dt = new Rule(ss[0], ss[1]);
//                rulesbase.add(dt);
                tapkl[i] = ss[1];
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadsk() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\data\\sukien.dat"), "UTF8"))) {

            String sCurrentLine;
            int i = 0;
//            tapsk  = new String[sosk];
            Sukien sk;
            while ((sCurrentLine = br.readLine()) != null)//trong khi chưa kết thúc file
            {
                String[] ss = sCurrentLine.split("[|]");
                System.out.println(ss[0]);
                System.out.println(ss[1]);
                sk = new Sukien(ss[0], ss[1]);
                facts.add(sk);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadskTP() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\data\\sukien.dat"), "UTF8"))) {

            String sCurrentLine;
            int i = 0;
            //System.out.println(sosk);
            tapsk = new String[sosk];
            tapskt = new String[sosk];
            while ((sCurrentLine = br.readLine()) != null)//trong khi chưa kết thúc file
            {
                String[] ss = sCurrentLine.split("[|]");
                tapsk[i] = ss[1];
                tapskt[i] = ss[0];
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // đếm đoọ dài arraylist
    private int FileCount(String path) {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"))) {
            while ((br.readLine()) != null)//trong khi chưa kết thúc file
            {
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(i);
        return i;

    }

    
    public void TimLuat(ArrayList tapluat, String tg, ArrayList sat) {
        for (int i = 0; i < tapluat.size(); i++) {
            //khai báo dt là đối tượng Luật và lấy luật thứ i về gán cho dt
            Rule dt = (Rule) tapluat.get(i);
            String[] ss = dt.getLeft().split("[ ]");//tách vế trái thành các sự kiện
            System.out.println("Timluat0"+ ss[0]);
            //System.out.println("Timluat1"+ ss[1]);
            int j = 0;

            while (!huyluat.contains(String.valueOf(i))//luật chưa bị xóa
                    && !sat.contains(i) && //tập sat chưa chứa luật i
                    j < ss.length && // chưa duyệt hết sự kiện ở vế trái
                    tg.indexOf(ss[j]) != -1)//trung gian có chứa sự kiện ss[j]
            {
                j++;
            }
            if (j == ss.length) {
                sat.add(i);//vế trái thuộc tg nên thêm luật i vào sat
            }
        }
    }

    private void suydientien() {
        ArrayList sat = new ArrayList();//khai báo tập sat để chứa các luật   
        loadskTP();
        factsbase = " a1";
        //gán biến tg bằng giả thiết factsbase
        for (String a : tapkl) {
            String tg = factsbase;
            vet = ""; //gán vết bằng rỗng
            // txtKetqua.Text = "";
            huyluat = "";//khởi tạo hủy luật bằng rỗng
            TimLuat(rulesbase, tg, sat);//tìm luật có vt thuộc tg đưa vào sat
            while (sat.size() > 0)//trong khi tập sat khác rỗng
            {
                System.out.println(sat.get(0).toString());
                int cs = (int) Integer.parseInt(sat.get(0).toString());//lấy trong tập sat 1 chỉ số luật và gán cho cs
                System.out.println("cs=" + cs);
                Rule r = (Rule) rulesbase.get(cs);//lấy luật ứng với cs và gán cho r
                sat.remove(0);//xóa luật ở vị trí đầu tiên trong sat
                tg = tg + " " + r.getRight();//thêm vế phải của luật r vào tg
                huyluat = huyluat + " " + String.valueOf(cs);//hủy luật r
                vet = vet + String.valueOf(cs) + " ";//thêm cs luật r vào vết
                if (tg.contains(a)) //nếu kết luận thuộc tg thì inkq
                {
                    vet = vet.trim();
                    String[] ss = vet.split("[ ]");//tách vet thành các cs luật 
                    for (int i = 0; i < ss.length; i++) {
                        int vt = Integer.parseInt(ss[i]);//chuyển chuỗi ss[i] thành số
                        Rule l = (Rule) rulesbase.get(vt);//lấy luật tại vt về gán cho l
                        String s = String.format("{0}->{1}\r\n", l.getLeft(), l.getRight());//định dạng để in luật
                    }
                    for (String tempkl : tapskt)
                        {
                            if (factsbase.contains(tempkl))
                            {
//                                int ii = Array.indexOf(tapskt, tempkl);
                                int ii = tapskt.toString().indexOf(tempkl);
                                String kl = tapsk[ii];
                                System.out.println("kl: "+ kl);
//                                txtKetqua.Text = txtKetqua.Text + tempkl + "->" + kl + "\r\n";
                            }

                        }
                    int iii = tapskt.toString().indexOf(a);
                    String kl1 = tapsk[iii];
                    System.out.println("Diagnose: " + kl1 + "\r\n");

                    return; //kết thúc suy diễn
                }
                TimLuat(rulesbase, tg, sat);//tìm luật lại
            }//while
            System.out.println("There is no law");
        }
    }

    public static void main(String[] args) {
        suydien s = new suydien();
        s.suydientien();
    }
}
