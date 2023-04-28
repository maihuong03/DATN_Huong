package cstt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ghiLuatVaoFile {
	// mã điều kiện
	final static String A1 = "A1"; // coffee
	final static String A2 = "A2"; // water
	final static String A3 = "A3"; // soda
	final static String A4 = "A4"; // young
	final static String A5 = "A5"; // middle-age
	final static String A6 = "A6"; // old
	final static String A7 = "A7"; // music
	final static String A8 = "A8"; // movie
	final static String A9 = "A9"; // newspaper
	final static String A10 = "A10"; // sweetness
	final static String A11 = "A11"; // sour
	final static String A12 = "A12"; // bitterness
	final static String A13 = "A13"; // good health
	final static String A14 = "A14"; // normal health
	final static String A15 = "A15"; // bad health
	final static String A16 = "A16"; // spring
	final static String A17 = "A17"; // summer
	final static String A18 = "A18"; // autumn
	final static String A19 = "A19"; // winter
	final static String A20 = "A20"; // snack
	final static String A21 = "A21"; // fried food
	final static String A22 = "A22"; // cake
	final static String A23 = "A23";// fruits
	final static String A24 = "A24"; // sweet food

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ArrayList<Rule> dssk = new ArrayList<>();
		Rule sk1 = new Rule(1, new String[] { A1 }, "coffe", "Mang coffee đến");
		Rule sk2 = new Rule(2, new String[] {A1}, "coffe", "Mang coffe hòa tan đến");
		Rule sk3 = new Rule(3, new String[] {A1, A12}, "coffe đắng", "Mang coffe đen đến");
		Rule sk4 = new Rule(4, new String[] {A1, A12}, "coffe đắng", "Mang coffe đen đá đến");
		Rule sk5 = new Rule(5, new String[] {A1, A12}, "coffe đắng", "Mang coffe đen đá không đường đến");
		Rule sk6 = new Rule(6, new String[] {A1, A10}, "coffe ngọt", "mang coffe capuchino đến");
		Rule sk7 = new Rule(7, new String[] {A1, A10}, "coffe ngọt", "mang coffe capuchino caramel đến");
		Rule sk8 = new Rule(8, new String[] {A1, A10}, "coffe ngọt", "mang coffe capuchino dừa đến");
		Rule sk9 = new Rule(9, new String[] {A2}, "nước lọc", "mang nưóc lọc đến");
		Rule sk10 = new Rule(10, new String[] {A2}, "nước lọc", "mang nước lọc đóng chai đến");
		Rule sk11 = new Rule(11, new String[] {A2}, "nước lọc", "mang nước sôi đến");
		Rule sk12 = new Rule(12, new String[] {A2}, "nước lọc", "mang nước lạnh đến");
		Rule sk13 = new Rule(13, new String[] {A3}, "nước ngọt", "mang nước ngọt bất kì");
		Rule sk14 = new Rule(14, new String[] {A3}, "nước ngọt", "mang cocacola lon đến");
		Rule sk15 = new Rule(15, new String[] {A3}, "nước ngọt", "mang pepsi lon đến");
		Rule sk16 = new Rule(16, new String[] {A3}, "nước ngọt", "mang trà xanh không độ đến");
		Rule sk17 = new Rule(17, new String[] {A3}, "nước ngọt", "mang nước tăng lực number one đến");
		Rule sk18 = new Rule(18, new String[] {A3, A11}, "nước ngọt vị chanh", "mang 7up lon đến");
		Rule sk19 = new Rule(19, new String[] {A3, A11}, "nước ngọt vị chanh", "mang nước cam twister");
		Rule sk20 = new Rule(20, new String[] {A7}, "nhạc", "mở nhạc thể loại bất kì");
		Rule sk21 = new Rule(21, new String[] {A7, A4}, "nhạc trẻ", "đến mở nhạc trẻ");
		Rule sk22 = new Rule(22, new String[] {A7, A4}, "nhạc trẻ", "mở nhạc K-POP");
		Rule sk23 = new Rule(23, new String[] {A7, A4}, "nhạc trẻ", "mở nhạc V-POP");
		Rule sk24 = new Rule(24, new String[] {A7, A5}, "nhạc trung niên", "mở nhạc cổ điển");
		Rule sk25 = new Rule(25, new String[] {A7, A5}, "nhạc trung niên", "mở nhạc lãng mạn");
		Rule sk26 = new Rule(26, new String[] {A7, A6}, "nhạc cao tuổi", "mở nhạc cách mạng");
		Rule sk27 = new Rule(27, new String[] {A7, A6}, "nhạc cao tuổi", "mở nhạc bolero");
		Rule sk28 = new Rule(28, new String[] {A8}, "phim", "mở TV");
		Rule sk29 = new Rule(29, new String[] {A8}, "phim", "mở TV và bật chưong trình bất kì");
		Rule sk30 = new Rule(30, new String[] {A8, A4}, "phim cho người trẻ tuổi", "bật phim đang trên top thịnh hành");
		Rule sk31 = new Rule(31, new String[] {A8, A4}, "phim cho người trẻ tuổi", "bật phim trên netflix");
		Rule sk32 = new Rule(32, new String[] {A8, A4}, "phim cho người trẻ tuổi", "bật phim  hành động");
		Rule sk33 = new Rule(33, new String[] {A8, A5}, "phim cho người trung tuổi", "bật phim kiếm hiệp");
		Rule sk34 = new Rule(34, new String[] {A8, A5}, "phim cho người trung tuổi", "bật phim cổ trang");
		Rule sk35 = new Rule(35, new String[] {A8, A6}, "phim cho người cao tuổi", "bật phim cách mạng");
		Rule sk36 = new Rule(36, new String[] {A8, A6}, "phim cho người cao tuổi", "bật phim tư liệu về Bác Hồ");
		Rule sk37 = new Rule(37, new String[] {A9, A4}, "người trẻ tuổi thích đọc báo", "mở báo mạng");
		Rule sk38 = new Rule(38, new String[] {A9, A4}, "người trẻ tuổi thích đọc báo", "mở trang dantri.com để đọc báo mạng");
		Rule sk39 = new Rule(39, new String[] {A9, A5}, "người trung tuổi đọc báo", "mang máy đọc báo đến");
		Rule sk40 = new Rule(40, new String[] {A9, A5}, "người trung tuổi đọc báo", "mang báo lao động đến");
		Rule sk41 = new Rule(41, new String[] {A9, A6}, "người cao tuổi đọc báo", "mang nhật báo đến");
		Rule sk42 = new Rule(42, new String[] {A9, A6}, "người cao tuổi đọc báo", "mang báo An ninh thủ đô đến");
		Rule sk43 = new Rule(43, new String[] {A9, A6}, "người cao tuổi đọc báo", "mang báo Nhân dân đến");
		Rule sk44 = new Rule(44, new String[] {A13}, "sức khỏe tốt", "đến kiểm tra sức khỏe định kì");
		Rule sk45 = new Rule(45, new String[] {A14}, "sức khỏe bình thường", "đến khám sức khỏe và kê đơn thuốc");
		Rule sk46 = new Rule(46, new String[] {A14}, "sức khỏe bình thường", "mang thuốc đã kê đơn đến");
		Rule sk47 = new Rule(47, new String[] {A15}, "sức khỏe có vấn đề", "mang thuốc giảm đau đầu đến");
		Rule sk48 = new Rule(48, new String[] {A15}, "sức khỏe có vấn đề", "mang thuốc giảm đau bụng");
		Rule sk49 = new Rule(49, new String[] {A15}, "sức khỏe có vấn đề", "mang thuốc giải rượu đến");
		Rule sk50 = new Rule(50, new String[] {A15}, "sức khỏe có vấn đề", "mang thuốc trị ho đến");
		Rule sk51 = new Rule(51, new String[] {A15}, "sức khỏe có vấn đề", "mang thuốc xương khớp đến");
		Rule sk52 = new Rule(52, new String[] {A16}, "hiện tại đang là mùa xuân", "bật nhạc tết lên để tạo không khí đón xuân về");
		Rule sk53 = new Rule(53, new String[] {A16}, "hiện tại đang là mùa xuân", "đến chúc những lời chúc thân thương nhất");
		Rule sk54 = new Rule(54, new String[] {A16}, "hiện tại đang là mùa xuân", "đến nhắc nhở dọn dẹp phòng để đón tết");
		Rule sk55 = new Rule(55, new String[] {A17}, "hiện tại đáng là mùa hè", "bật điều hòa lên");
		Rule sk56 = new Rule(56, new String[] {A17}, "hiện tại đáng là mùa hè", "bật quạt trần lên");
		Rule sk57 = new Rule(57, new String[] {A17}, "hiện tại đáng là mùa hè", "bật quạt treo tường lên");
		Rule sk58 = new Rule(58, new String[] {A18}, "hiện tại đang là mùa thu", "mở cửa sổ phòng cho thoáng mát");
		Rule sk59 = new Rule(59, new String[] {A18}, "hiện tại đang là mùa thu", "mở cửa sổ đón gió thu");
		Rule sk60 = new Rule(60, new String[] {A19}, "hiện tại đang là mua đông", "bật lò sưởi");
		Rule sk61 = new Rule(61, new String[] {A19}, "hiện tại đang là mua đông", "nhắc nhở mặc đồ ấm trước khi ra đường");
		Rule sk62 = new Rule(62, new String[] {A19}, "hiện tại đang là mua đông", "nhắc nhở uống nước ấm để giữ gìn sức khỏe");
		Rule sk63 = new Rule(63, new String[] {A20}, "snack", "mang bim bim đến");
		Rule sk64 = new Rule(64, new String[] {A20}, "snack", "mang snack khoai tây đến");
		Rule sk65 = new Rule(65, new String[] {A21}, "đồ ăn chiên", "mang khoai tây chiên đến");
		Rule sk66 = new Rule(66, new String[] {A21}, "đồ ăn chiên", "mang khoai lang chiên đến");
		Rule sk67 = new Rule(67, new String[] {A21}, "đồ ăn chiên", "mang gà rán đến đến");
		Rule sk68 = new Rule(68, new String[] {A22}, "bánh", "mang bánh quy đến");
		Rule sk69 = new Rule(69, new String[] {A22}, "bánh", "mang bánh quy đến");
		Rule sk70 = new Rule(67, new String[] {A22, A10}, "bánh ngọt", "mang bánh gateaux đến");
		Rule sk71 = new Rule(71, new String[] {A22, A10}, "bánh ngọt", "mang bánh su kem đến");
		Rule sk72 = new Rule(72, new String[] {A22, A10}, "bánh ngọt", "mang bánh choco-pie đến đến");
		Rule sk73 = new Rule(73, new String[] {A23}, "trái cây", "mang trái cây đến");
		Rule sk74 = new Rule(74, new String[] {A23, A10}, "trái cây có vị ngọt", "mang táo đến");
		Rule sk75 = new Rule(75, new String[] {A23, A10}, "trái cây có vị ngọt", "mang chuối đến");
		Rule sk76 = new Rule(76, new String[] {A23, A11}, "trái cây có vị chua", "mang xoài xanh đến");
		Rule sk77 = new Rule(77, new String[] {A23, A11}, "trái cây có vị chua", "mang quả cóc đến đến");
		Rule sk78 = new Rule(78, new String[] {A23, A11}, "trái cây có vị chua", "mang xoài dầm đến đến");
		Rule sk79 = new Rule(79, new String[] {A24}, "đồ ăn vặt ngọt", "mang chocolate đến");
		Rule sk80 = new Rule(80, new String[] {A24}, "đồ ăn ngọt", "mang mứt hoa quả đến");

		dssk.add(sk1);
		dssk.add(sk2);
		dssk.add(sk3);
		dssk.add(sk4);
		dssk.add(sk5);
		dssk.add(sk6);
		dssk.add(sk7);
		dssk.add(sk8);
		dssk.add(sk9);
		dssk.add(sk10);
		dssk.add(sk11);
		dssk.add(sk12);
		dssk.add(sk13);
		dssk.add(sk14);
		dssk.add(sk15);
		dssk.add(sk16);
		dssk.add(sk17);
		dssk.add(sk18);
		dssk.add(sk19);
		dssk.add(sk20);
		dssk.add(sk21);
		dssk.add(sk22);
		dssk.add(sk23);
		dssk.add(sk24);
		dssk.add(sk25);
		dssk.add(sk26);
		dssk.add(sk27);
		dssk.add(sk28);
		dssk.add(sk29);
		dssk.add(sk30);
		dssk.add(sk31);
		dssk.add(sk32);
		dssk.add(sk33);
		dssk.add(sk34);
		dssk.add(sk35);
		dssk.add(sk36);
		dssk.add(sk37);
		dssk.add(sk38);
		dssk.add(sk39);
		dssk.add(sk40);
		dssk.add(sk41);
		dssk.add(sk42);
		dssk.add(sk43);
		dssk.add(sk44);
		dssk.add(sk45);
		dssk.add(sk46);
		dssk.add(sk47);
		dssk.add(sk48);
		dssk.add(sk49);
		dssk.add(sk50);
		dssk.add(sk51);
		dssk.add(sk52);
		dssk.add(sk53);
		dssk.add(sk54);
		dssk.add(sk55);
		dssk.add(sk56);
		dssk.add(sk57);
		dssk.add(sk58);
		dssk.add(sk59);
		dssk.add(sk60);
		dssk.add(sk61);
		dssk.add(sk62);
		dssk.add(sk63);
		dssk.add(sk64);
		dssk.add(sk65);
		dssk.add(sk66);
		dssk.add(sk67);
		dssk.add(sk68);
		dssk.add(sk69);
		dssk.add(sk70);
		dssk.add(sk71);
		dssk.add(sk72);
		dssk.add(sk73);
		dssk.add(sk74);
		dssk.add(sk75);
		dssk.add(sk76);
		dssk.add(sk77);
		dssk.add(sk78);
		dssk.add(sk79);
		dssk.add(sk80);
		
		Rule.ghi("tapluat2.dat", dssk);
		System.out.println("Complete");
	}
}
