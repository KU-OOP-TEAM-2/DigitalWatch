package GUI;

public class ImageDir {
	public static String path = ImageDir.class.getResource("").getPath();
	public static String abs_p = "../ImageFiles/";

	//NUMBERS
	public static String SegDead14Big_dir = path + abs_p + "seg14Dead_45.png";
	public static String SegDead14_dir = path + abs_p + "seg14Dead_18.png";
	public static String num0big_dir = path + abs_p + "number0Seg_45.png";
	public static String num0_dir = path + abs_p + "number0Seg_18.png";

	//ALPHABETS
	public static String fSeg_dir = path + abs_p + "alphaFSeg_18.png";
	public static String nSeg_dir = path + abs_p + "alphaNSeg_18.png";

	//SIGNS
	public static String colon_dir = path + abs_p + "colon_14.png";
	public static String colonBig_dir = path + abs_p + "colon_45.png";

	//ICONS
	public static String clock_dir = path + abs_p + "clock45.png";
	public static String clockDead_dir = path + abs_p + "clockDead45.png";
}

