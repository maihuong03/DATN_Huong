/*
	 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cstt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 24h
 */
public class test {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ArrayList<Rule> dssk = new ArrayList<>();
		Rule sk1 = new Rule(1, "it ban", "lau ki");
		Rule sk2 = new Rule(2, "sach", "launhanh");
		Rule sk3 = new Rule(3, "ban", "lau ki");
		Rule sk4 = new Rule(4, "rat ban", "lau rat ki");
		Rule sk5 = new Rule(5, "vat can co dinh dang truoc", "re phai");
		Rule sk6 = new Rule(6, "vat can co dinh dang truoc", "re trai");
		Rule sk7 = new Rule(7, "vat can co dinh dang truoc va ben phai la o da lau", "re trai");
		Rule sk8 = new Rule(8, "vat can co dinh dang truoc va ben trai la o da lau", "re phai");
		Rule sk9 = new Rule(9, "vat can co dinh dang truoc va ben phai la tuong", "re trai");
		Rule sk10 = new Rule(10, "vat can co dinh dang truoc va ben trai la tuong", "re phai");
		Rule sk11 = new Rule(11, "vat can co dinh dang truoc va vat can co dinh ben trai", "re phai");
		Rule sk12 = new Rule(12, "vat can co dinh dang truoc va vat can co dinh ben phai", "re trai");
		Rule sk13 = new Rule(13, "vat can co dinh dang truoc va vat can co dinh ben trai va vat can co dinh ben phai",
				"quay lai o da lau");
		Rule sk14 = new Rule(14, "vat can co dinh dang truoc va vat can co dinh ben trai va ben phai la tuong",
				"quay lai o da lau");
		Rule sk15 = new Rule(15, "vat can co dinh dang truoc va vat can co dinh ben trai va vat can co dinh ben phai",
				"quay lai o da lau");
		Rule sk16 = new Rule(16, "vat can di dong dang truoc", "dung cho");
		Rule sk17 = new Rule(17, "vat can di dong dang truoc", "re phai");
		Rule sk18 = new Rule(18, "vat can di dong dang truoc", "re trai");
		Rule sk19 = new Rule(19, "vat can di dong dang truoc va ben phai la tuong", "re trai");
		Rule sk20 = new Rule(20, "vat can di dong dang truoc va ben trai la tuong", "re phai");
		Rule sk21 = new Rule(21, "vat can di dong dang truoc va ben trai la vat can co dinh", "dung cho");
		Rule sk22 = new Rule(22, "vat can di dong dang truoc va ben trai la vat can co dinh", "re phai");
		Rule sk23 = new Rule(23, "vat can di dong dang truoc va ben phai la vat can co dinh", "dung cho");
		Rule sk24 = new Rule(24, "vat can di dong dang truoc va ben phai la vat can co dinh", "re trai");
		Rule sk25 = new Rule(25, "vat can di dong dang truoc va ben phai la tuong", "dung cho");
		Rule sk26 = new Rule(26, "vat can di dong dang truoc va ben phai la tuong", "re trai");
		Rule sk27 = new Rule(27, "vat can di dong dang truoc va ben trai la tuong", "dung cho");
		Rule sk28 = new Rule(28, "vat can di dong dang truoc va ben phai la tuong", "re phai");
		Rule sk29 = new Rule(29, "vat can di dong dang truoc va ben trai la tuong va ben phai la vat can co dinh",
				"dung cho");
		Rule sk30 = new Rule(30, "vat can di dong dang truoc va ben trai la tuong va ben phai la vat can co dinh",
				"quay lai o da lau");
		Rule sk31 = new Rule(31,
				"vat can di dong dang truoc va ben trai la vat can co dinh va ben phai la vat can co dinh", "dung cho");
		Rule sk32 = new Rule(32,
				"vat can di dong dang truoc va ben trai la vat can co dinh va ben phai la vat can co dinh",
				"quay lai o da lau");
		Rule sk33 = new Rule(33, "vat can di dong dang truoc v� vat can di dong ben trai va vat can di dong ben phai",
				"dung cho");
		Rule sk34 = new Rule(34, "vet ban la do vat", "bo vao thung rac");
		Rule sk35 = new Rule(35, "vet ban la nuoc", "lau kho");
		Rule sk36 = new Rule(36, "vet ban la dau mo", "chon hoa chat la bot mi");
		Rule sk37 = new Rule(37, "vat can di dong dang truoc va toc do cua vat can lon hon toc do cua ro bot",
				"dung cho");
		Rule sk38 = new Rule(38, "vat can di dong dang truoc va toc do cua vat can nho hon toc do ro bot", "dung cho");
		Rule sk39 = new Rule(39, "vat can di dong dong truoc va toc do cua vat can bang toc do cua ro bot", "dung cho");
		Rule sk40 = new Rule(40, "ben phai la canh tuong", "lau rat ki");
		Rule sk41 = new Rule(41, "dang truoc la vat can co dinh", "lau rat ki");
		Rule sk42 = new Rule(42, "ben phai la vat can co dinh", "lau rat ki");
		Rule sk43 = new Rule(43, "ben trai la vat can co dinh", "lau rat ki");
		Rule sk44 = new Rule(44, "ben trai la canh tuong", "lau rat ki");
		Rule sk45 = new Rule(45, "ben phai la canh tuong", "lau rat ki");
		Rule sk46 = new Rule(46, "vet ban la nuoc che", "lau rat ki");
		Rule sk47 = new Rule(47, "vet ban la cao su", "lau rat ki");
		Rule sk48 = new Rule(48, "vet ban la vet ri sat", "lau rat ki");
		Rule sk49 = new Rule(49, "vet ban la vet o", "lau rat ki");
		Rule sk50 = new Rule(50, "san nha la san go", "hut bui lau kho");
		Rule sk51 = new Rule(51, "san nha la san go va vet ban la nuoc", "hut bui lau kho");
		Rule sk52 = new Rule(52, "san nha la san go va vet ban la vet o", "lau bang khan am");
		Rule sk53 = new Rule(53, "san nha la san go va vet ban la vet chan cho meo", "lau bang khan am");
		Rule sk54 = new Rule(54, "san nha la san go va vet ban la chi mau", "chon hoa chat la kem danh rang ");
		Rule sk55 = new Rule(55, "san nha la san go va vet ban la son moi",
				"chon hoa chat la nuoc lau san lam am khan");
		Rule sk56 = new Rule(56, "san nha la san go va vet ban o cac khe", "hut bui");
		Rule sk57 = new Rule(57, "vet ri sat trong nen nha tam va san nha la da mai", "dung hoa chat la muoi ");
		Rule sk58 = new Rule(58, "vet ri sat trong nen nha tam va san nha la xi mang", "dung hoa chat la muoi ");
		Rule sk59 = new Rule(59, "vet ri sat trong nen nha tam va san nha la men s?", "dung hoa chat la giam ");
		Rule sk60 = new Rule(60, "vet can bam tren san va san la men su", "chon hoa chat la voi, nuoc oxigia ");
		Rule sk61 = new Rule(61, "vet can bam tren san va san la gach da hoa", "chon hoa chat nuoc oxigia ");
		Rule sk62 = new Rule(62, "san nha la san go va vet ban la chi mau va chon hoa chat la kem danh rang",
				"lau ki bang khan kho");
		Rule sk63 = new Rule(63, "san nha la san go va vet ban la son moi va chon hoa chat la nuoc lau san lam am khan",
				"lau ki");
		Rule sk64 = new Rule(64, "san nha la san go va vet ban o cac khe va hut bui", "lau rat ki");
		Rule sk65 = new Rule(65, "vet ri sat trong nen nha tam va san nha la da mai va dung hoa chat la muoi",
				"cha ki");
		Rule sk66 = new Rule(66, "vet ri sat trong nen nha tam va san nha la xi mang va dung hoa chat la muoi",
				"cha ki");
		Rule sk67 = new Rule(67, "vet ri sat trong nen nha tam va san nha la men s? va dung hoa chat la giam",
				"cha ki");
		Rule sk68 = new Rule(68, "vet can bam tren san va san la men su chon hoa chat la voi", "do vao vet ban");
		Rule sk76 = new Rule(76, "vet can bam tren san va san la men su chon hoa chat la voi va do vao vet ban",
				"dung cho");
		Rule sk69 = new Rule(69,
				"vet can bam tren san va san la men su chon hoa chat la voi va chon hoa chat nuoc oxigia va", "cha ki");
		Rule sk70 = new Rule(70, "vet can bam tren san va san la gach da hoa va chon hoa chat nuoc oxigia ",
				"do vao vet ban");
		Rule sk77 = new Rule(77,
				"vet can bam tren san va san la gach da hoa va chon hoa chat nuoc oxigia va do vao vet ban ",
				"dung cho ");
		Rule sk71 = new Rule(71,
				"vet can bam tren san va san la gach da hoa va chon hoa chat nuoc oxigia va chon thuoc danh bong",
				"cha ki");
		Rule sk72 = new Rule(72, "vet can va san nha la gach bong", "chon hoa chat chanh");
		Rule sk73 = new Rule(73, "vet can va san nha la gach bong va chon hoa chat chanh", "do vao vet ban");
		Rule sk74 = new Rule(74, "vet can va san nha la gach bong va chon hoa chat chanh va o vao vet ban", "dung cho");
		Rule sk75 = new Rule(75, "vet can va san nha la gach bong va chon hoa chat chanh va o vao vet ban va dung cho",
				"cha ki");
		Rule sk78 = new Rule(78, "vet can va san nha la gach trang men", "chon hoa chat chanh");
		Rule sk79 = new Rule(79, "vet can va san nha la gach trang men va chon hoa chat chanh", "do vao vet ban");
		Rule sk80 = new Rule(80, "vet can va san nha la gach trang men va chon hoa chat chanh va o vao vet ban",
				"dung cho");
		Rule sk81 = new Rule(81,
				"vet can va san nha la gach trang men va chon hoa chat chanh va o vao vet ban va dung cho", "cha ki");
		Rule sk82 = new Rule(82, "vet can va san nha la san da mai", "chon hoa chat dung dich muoi+giam+soda");
		Rule sk83 = new Rule(83, "vet can va san nha la gach trang men va chon hoa chat dung dich muoi+giam+soda",
				"do vao vet ban");
		Rule sk84 = new Rule(84,
				"vet can va san nha la gach trang men va chon hoa chat dung dich muoi+giam+soda va o vao vet ban",
				"dung cho");
		Rule sk85 = new Rule(85,
				"vet can va san nha la gach trang men va chon hoa chat dung dich muoi+giam+soda va o vao vet ban va dung cho",
				"cha ki");
		Rule sk86 = new Rule(86, "san gach bong va b? xin", "lau kho bang nuoc");
		Rule sk87 = new Rule(87, "san gach bong va b? xin va lau kho bang nuoc", "dung cho cho kho");
		Rule sk88 = new Rule(88, "san gach bong va b? xin va lau kho bang nuoc va dung cho cho kho",
				"chon hoa chat botmi+botgao+dauan");
		Rule sk89 = new Rule(89,
				"san gach bong va b? xin va lau kho bang nuoc va dung cho cho kho va chon hoa chat botmi+botgao+dauan",
				"miet cho bong");
		Rule sk90 = new Rule(90,
				"san gach bong va b? xin va lau kho bang nuoc va dung cho cho kho va chon hoa chat botmi+botgao+dauan va miet cho bong",
				"dung cho");
		Rule sk91 = new Rule(91,
				"san gach bong va b? xin va lau kho bang nuoc va dung cho cho kho va chon hoa chat botmi+botgao+dauan va miet cho bong va dung cho",
				"chon nuoc lau san");
		Rule sk92 = new Rule(92,
				"san gach bong va b? xin va lau kho bang nuoc va dung cho cho kho va chon hoa chat botmi+botgao+dauan va miet cho bong va dung cho va chon nuoc lau san",
				"lau sach");
		Rule sk93 = new Rule(93, "san nha la tham trai nha", "hut bui");
		Rule sk94 = new Rule(94, "san nha la tham trai nha va vet ban la vet o", "chon hoa chat la soda");
		Rule sk95 = new Rule(95, "san nha la tham trai nha va vet ban la vet o va chon hoa chat la soda",
				"do vao vet o");
		Rule sk96 = new Rule(95, "san nha la da granite", "chon nuoc lau san");
		Rule sk97 = new Rule(97, "san nha la da granite va chon nuoc lau san", "lau sach");
		Rule sk98 = new Rule(98, "san nha la da granite va chon nuoc lau san va da lau sach", "lam kho");
		Rule sk99 = new Rule(99, "vet ban la dau mo va chon hoa chat la bot mi", "do hoa chat vao vet ban");
		Rule sk100 = new Rule(100, "vet ban la dau mo va chon hoa chat la bot mi va do hoa chat vao vet ban",
				"dung cho");
		Rule sk101 = new Rule(101,
				"vet ban la dau mo va chon hoa chat la bot mi va do hoa chat vao vet ban va dung cho", "cha ki");
		Rule sk102 = new Rule(102,
				"vet ban la dau mo va chon hoa chat la bot mi va do hoa chat vao vet ban va dung cho va cha ki",
				"quet sach");
		Rule sk103 = new Rule(103,
				"vet ban la dau mo va chon hoa chat la bot mi va do hoa chat vao vet ban va dung cho va cha ki va duoc lam sach",
				"chon nuoc lau san");
		Rule sk104 = new Rule(104,
				"vet ban la dau mo va chon hoa chat la bot mi va do hoa chat vao vet ban va dung cho va cha ki va duoc lam sach chon nuoc lau san",
				"lau sach");
		Rule sk105 = new Rule(105, "it ban va chon nuoc lau san", "lau sach");
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
		dssk.add(sk81);
		dssk.add(sk82);
		dssk.add(sk83);
		dssk.add(sk84);
		dssk.add(sk85);
		dssk.add(sk86);
		dssk.add(sk87);
		dssk.add(sk88);
		dssk.add(sk89);
		dssk.add(sk90);
		dssk.add(sk91);
		dssk.add(sk92);
		dssk.add(sk93);
		dssk.add(sk94);
		dssk.add(sk95);
		dssk.add(sk96);
		dssk.add(sk97);
		dssk.add(sk98);
		dssk.add(sk99);
		dssk.add(sk100);
		dssk.add(sk101);
		dssk.add(sk102);
		dssk.add(sk103);
		dssk.add(sk104);
		dssk.add(sk105);

		Rule.ghi("tapluat.dat", dssk);

	}
}