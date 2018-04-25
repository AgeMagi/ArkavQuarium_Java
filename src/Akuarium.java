import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Akuarium extends JPanel {
  public static int SCREEN_WIDTH = 853;
  public static int SCREEN_HEIGHT = 640;
  public static double PI = 3.14159265;
  public static int HOME = 0;
  public static int PLAY = 1;

  private static final String BACKGROUND_IMAGE = "image/Aquarium6.jpg";
  private static final String TOOLBAR_IMAGE = "image/tabatas.png";
  private static final String MAINMENU_IMAGE = "image/mainmenu.png";

  public static final String parentFolder = "../../../";

  private final String abspath = Akuarium.class.getProtectionDomain().getCodeSource().getLocation()
          .getPath().replaceAll("%20", " ");

  private BufferedImage defaultImage;
  private Map<String, BufferedImage> images;
  private JFrame jframe;
  private static long now;
  private static long start;

  private boolean mainmenu = true;
  private boolean menang = false;
  private boolean kalah = false;
  private boolean kurangkoin = false;

  private Player player = new Player();
  private List<Ikan> ikan;
  private List<MakananIkan> makananikan;
  private List<Koin> koin;
  Siput siput;

  public static double time_since_start() {
    return (now - start) / (1e9);
  }

  /**
   * Constructor dengan parameter.
   * @param defaultObjectImagePath .
   * @throws IOException .
   */
  public Akuarium(String defaultObjectImagePath) throws IOException {
    Random rand = new Random();

    this.defaultImage = ImageIO.read(new File(defaultObjectImagePath));
    this.images = new HashMap<>();

    ikan = new List<Ikan>();
    makananikan = new List<MakananIkan>();
    koin = new List<Koin>();
    siput = new Siput();
  }

  private BufferedImage readImage(String path) {
    BufferedImage newImage = this.images.get(path);
    if (newImage == null) {
      try {
        newImage = ImageIO.read(new File(path));
      } catch (IOException e) {
        newImage = defaultImage;
      }
      this.images.put(path, newImage);
    }
    return newImage;
  }

  /**
   * Melakukan build pada jframe.
   */
  public void buildFrame() {
    jframe = new JFrame();
    jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jframe.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    jframe.setLocationRelativeTo(null);
    jframe.setVisible(true);
  }

  /**
   * Content apa saja yang terjadi pada from.
   */
  public void setContent() {
    jframe.setContentPane(this);
    jframe.getContentPane().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        double mouseX = e.getLocationOnScreen().getX();
        double mouseY = e.getLocationOnScreen().getY();

        mouseX -= 291;
        mouseY -= 105;

        if (mainmenu) {
          if ((mouseX <= 773 && mouseX >= 619) && (mouseY <= 471 && mouseY >= 324)) {
            mainmenu = false;
          } else if ((mouseX <= 637 && mouseX >= 541) && (mouseY <= 275 && mouseY >= 188)) {
            try {
              load();
            } catch (IOException error) {
              System.out.println("ERROR Load");
            }
          }
        } else {
          int ketemu = -1;
          for (int i = 0; i < koin.getSize() && ketemu == -1; i++) {
            if (Math.abs(koin.getIdx(i).getX() - mouseX) < 30
                    && Math.abs(koin.getIdx(i).getY() - mouseY - 100) < 30) {
              ketemu = i;
            }
          }
          if (ketemu != -1) {
            player.tambahKoin(koin.getIdx(ketemu).getNilai());
            koin.removeIdx(ketemu);
          } else {
            if ((mouseX <= 107 && mouseX >= 48) && (mouseY <= 66 && mouseY >= 23)) {
              if (player.getJumlahkoin() < 100) {
                kurangkoin = true;
              } else {
                Ikan newguppy = new Guppy(Main.rand.nextInt(SCREEN_WIDTH),
                        Main.rand.nextInt(SCREEN_HEIGHT), 0, 40000);
                ikan.add(newguppy);
                player.kurangkanKoin(100);
                kurangkoin = false;
              }
            } else if ((mouseX <= 221 && mouseX >= 160) && (mouseY <= 69 && mouseY >= 18)) {
              if (player.getJumlahkoin() < 200) {
                kurangkoin = true;
              } else {
                Ikan newpiranha = new Piranha(Main.rand.nextInt(SCREEN_WIDTH),
                        Main.rand.nextInt(SCREEN_HEIGHT), 0, 60000);
                ikan.add(newpiranha);
                player.kurangkanKoin(200);
                kurangkoin = false;
              }
            } else if ((mouseX <= 676 && mouseX >= 614) && (mouseY <= 67 && mouseY >= 18)) {
              if (player.getJumlahkoin() < 500) {
                kurangkoin = true;
              } else {
                player.kurangkanKoin(500);
                player.tambahTelur();
                kurangkoin = false;
                if (player.getBanyaktelur() == 3) {
                  menang = true;
                }
              }
            } else if ((mouseX >= 36 && mouseX <= 86) && (mouseY <= 604 && mouseY >= 578)) {
              try {
                save();
              } catch (IOException ioexception) {
                System.out.println("ERROR");
              }
            } else {
              if (player.getJumlahkoin() < 5) {
                kurangkoin = true;
              } else {
                MakananIkan newmakananikan = new MakananIkan(mouseX, mouseY + 100);
                makananikan.add(newmakananikan);
                player.kurangkanKoin(5);
                kurangkoin = false;
              }
            }
          }
        }
      }
    });
  }

  /**
   * Untuk melakukan save.
   * @throws IOException .
   */
  public void save() throws IOException {
    BufferedWriter fileikan = new BufferedWriter(new FileWriter("FileSave/Ikan.txt"));

    for (int i = 0; i < ikan.getSize(); i++) {
      fileikan.write(ikan.getIdx(i).getX() + " " + ikan.getIdx(i).getY());
      fileikan.write(" " + ikan.getIdx(i).getLapar() + " "
              + (ikan.getIdx(i).getWaktuMakan() - time_since_start()) + " "
              + ikan.getIdx(i).getType() + " " + ikan.getIdx(i).getPointTujuan().getX()
              + " " + ikan.getIdx(i).getPointTujuan().getY() + " " + ikan.getIdx(i).getImage()
              + " " + (ikan.getIdx(i).getWaktuRandom() - time_since_start()));
      if (ikan.getIdx(i).getType().equals("Guppy")) {
        fileikan.write(" " + (ikan.getIdx(i).getWaktuKoin() - time_since_start())
                + " " + ikan.getIdx(i).getLevel() + " "
                + ikan.getIdx(i).getJumlahMakanYangDimakan() + "\n");
      } else {
        fileikan.write("\n");
      }
    }

    BufferedWriter filemakananikan = new BufferedWriter(new FileWriter("FileSave/MakananIkan.txt"));

    for (int i = 0; i < makananikan.getSize(); i++) {
      filemakananikan.write(makananikan.getIdx(i).getX() + " " + makananikan.getIdx(i).getY());
      filemakananikan.write(" " + makananikan.getIdx(i).getImage() + "\n");
    }

    BufferedWriter filekoin = new BufferedWriter(new FileWriter("FileSave/Koin.txt"));

    for (int i = 0; i < koin.getSize(); i++) {
      filekoin.write(koin.getIdx(i).getX() + " " + koin.getIdx(i).getY()
              + " " + koin.getIdx(i).getKecepatan());
      filekoin.write(" " + koin.getIdx(i).getNilai() + " "
              + koin.getIdx(i).getLevel() + " " + koin.getIdx(i).getImage() + "\n");
    }

    BufferedWriter filesiput = new BufferedWriter(new FileWriter("FileSave/Siput.txt"));

    filesiput.write(siput.getX() + " " + siput.getY() + " "
            + siput.getPointtujuan().getX() + " " + siput.getPointtujuan().getY()
            + " " + siput.getImage() + "\n");

    BufferedWriter fileplayer = new BufferedWriter(new FileWriter("FileSave/Player.txt"));

    fileplayer.write(player.getJumlahkoin() + " " + player.getBanyaktelur() + "\n");

    fileplayer.close();
    filesiput.close();
    filekoin.close();
    filemakananikan.close();
    fileikan.close();
  }

  /**
   * Untuk melakukan load pada ArkavQuarium.
   * @throws IOException .
   */
  public void load() throws IOException {
    BufferedReader fileikan = new BufferedReader(new FileReader("FileSave/Ikan.txt"));

    String line;
    String[] splitline;

    while ((line = fileikan.readLine()) != null) {
      splitline = line.split(" ");

      if (splitline[4].equals("Guppy")) {
        Ikan newguppy = new Guppy(Double.parseDouble(splitline[0]),
                Double.parseDouble(splitline[1]), 0, 40000);

        newguppy.setLapar(Boolean.parseBoolean(splitline[2]));
        newguppy.setWaktuMakan(Double.parseDouble(splitline[3]));
        newguppy.getPointTujuan().setX(Double.parseDouble(splitline[5]));
        newguppy.getPointTujuan().setY(Double.parseDouble(splitline[6]));
        newguppy.setImage(splitline[7]);
        newguppy.setWaktuRandom(Double.parseDouble(splitline[8]));
        newguppy.setWaktuKoin(Double.parseDouble(splitline[9]));
        newguppy.setLevel(Integer.parseInt(splitline[10]));
        newguppy.setJumlahMakanYangDimakan(Integer.parseInt(splitline[11]));

        ikan.add(newguppy);
      } else {
        Ikan newpiranha = new Piranha(Double.parseDouble(splitline[0]),
                Double.parseDouble(splitline[1]), 0, 60000);

        newpiranha.setLapar(Boolean.parseBoolean(splitline[2]));
        newpiranha.setWaktuMakan(Double.parseDouble(splitline[3]));
        newpiranha.getPointTujuan().setX(Double.parseDouble(splitline[5]));
        newpiranha.getPointTujuan().setY(Double.parseDouble(splitline[6]));
        newpiranha.setImage(splitline[7]);
        newpiranha.setWaktuRandom(Double.parseDouble(splitline[8]));

        ikan.add(newpiranha);
      }
    }

    BufferedReader filekoin = new BufferedReader(new FileReader("FileSave/Koin.txt"));

    while ((line = filekoin.readLine()) != null) {
      splitline = line.split(" ");

      Koin newkoin = new Koin(Double.parseDouble(splitline[0]),
              Double.parseDouble(splitline[1]), Double.parseDouble(splitline[2]),
              Integer.parseInt(splitline[3]), Integer.parseInt(splitline[4]));

      koin.add(newkoin);
    }

    BufferedReader filemakananikan = new BufferedReader(new FileReader("FileSave/MakananIkan.txt"));

    while ((line = filemakananikan.readLine()) != null) {
      splitline = line.split(" ");

      MakananIkan newmakananikan = new MakananIkan(Double.parseDouble(splitline[0]),
              Double.parseDouble(splitline[1]));

      makananikan.add(newmakananikan);
    }

    BufferedReader filesiput = new BufferedReader(new FileReader("FileSave/Siput.txt"));

    line = filesiput.readLine();
    splitline = line.split(" ");
    siput.setX(Double.parseDouble(splitline[0]));
    siput.setY(Double.parseDouble(splitline[1]));

    BufferedReader fileplayer = new BufferedReader(new FileReader("FileSave/Player.txt"));

    line = fileplayer.readLine();
    splitline = line.split(" ");
    player.setJumlahkoin(Integer.parseInt(splitline[0]));
    player.setBanyaktelur(Integer.parseInt(splitline[1]));

    fileplayer.close();
    filesiput.close();
    filemakananikan.close();
    filekoin.close();
    fileikan.close();

    mainmenu = false;
  }

  /**
   * Untuk melakukan inisiasi pada Permainan.
   */
  public void startAkuarium() {
    playAkuarium();
  }

  /**
   * Untuk menjalankan frame pada ArkavKuaium.
   */
  public void playAkuarium() {
    Random rand = new Random();

    start = System.nanoTime();

    initDefault();

    while (true) {

      now = System.nanoTime();

      try {
        Thread.sleep(60);

        syncAll();


        jframe.invalidate();
        jframe.validate();
        jframe.repaint();
      } catch (InterruptedException e) {
        System.out.println("ERROR");
      }
    }
  }

  public void initDefault() {

  }

  /**
   * Untuk melakukan update frame pada arkavquarium.
   */
  public void syncAll() {

    int dapatkoin = siput.cariKoin(koin);

    if (dapatkoin != -1) {
      player.tambahKoin(koin.getIdx(dapatkoin).getNilai());
      koin.removeIdx(dapatkoin);
    }

    int loop = 0;
    while (loop < ikan.getSize()) {
      if (ikan.getIdx(loop).getType().equals("Guppy")) {
        if (ikan.getIdx(loop).mati()) {
          ikan.removeIdx(loop);
          continue;
        }
        if (ikan.getIdx(loop).getLapar()) {
          int makanandimakan = ikan.getIdx(loop).cariMakanGuppy(makananikan);
          if (makanandimakan != -1) {
            makananikan.removeIdx(makanandimakan);
          }
        } else {
          ikan.getIdx(loop).gerak();
        }
        if (ikan.getIdx(loop).keluarkanKoinGuppy()) {
          if (ikan.getIdx(loop).getLevel() == 1) {
            Koin newkoin = new Koin(ikan.getIdx(loop).getX(), ikan.getIdx(loop).getY(), 200, 10, 1);
            koin.add(newkoin);
          } else if (ikan.getIdx(loop).getLevel() == 2) {
            Koin newkoin = new Koin(ikan.getIdx(loop).getX(), ikan.getIdx(loop).getY(), 200, 20, 2);
            koin.add(newkoin);
          } else if (ikan.getIdx(loop).getLevel() == 3) {
            Koin newkoin = new Koin(ikan.getIdx(loop).getX(), ikan.getIdx(loop).getY(), 200, 50, 3);
            koin.add(newkoin);
          }
        }
      } else {
        if (ikan.getIdx(loop).mati()) {
          ikan.removeIdx(loop);
          continue;
        }
        if (ikan.getIdx(loop).getLapar()) {
          int ikandimakan = ikan.getIdx(loop).cariIkanTerdekat(ikan);
          if (ikandimakan != -1) {
            Koin newkoin = new Koin(ikan.getIdx(loop).getX(), ikan.getIdx(loop).getY(),
                    200, 100 * (ikan.getIdx(ikandimakan).getLevel() + 1), 4);
            koin.add(newkoin);
            ikan.removeIdx(ikandimakan);
          }
        } else {
          ikan.getIdx(loop).gerak();
        }
      }
      loop++;
    }

    for (int i = 0; i < makananikan.getSize(); i++) {
      makananikan.getIdx(i).gerak();
      if (Math.abs(makananikan.getIdx(i).getY() - SCREEN_HEIGHT - 70) < 5) {
        makananikan.removeIdx(i);
      }
    }

    for (int i = 0; i < koin.getSize(); i++) {
      koin.getIdx(i).gerak();
    }

    if (player.getJumlahkoin() < 100 && koin.getSize() == 0 && ikan.getSize() == 0) {
      kalah = true;
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.WHITE);

    if (mainmenu) {
      g.drawImage(readImage(MAINMENU_IMAGE), 0, 0, null);
    } else if (menang) {
      g.drawImage(readImage(BACKGROUND_IMAGE), 0, 0, null);
      g.drawImage(readImage(TOOLBAR_IMAGE), 0, 0, null);
      g.drawImage(readImage(abspath + parentFolder + "image/congratulations.png"),
              (SCREEN_WIDTH - 743) / 2, (SCREEN_HEIGHT - 551) / 2, null);
    } else if (kalah) {
      g.drawImage(readImage(BACKGROUND_IMAGE), 0, 0, null);
      g.drawImage(readImage(TOOLBAR_IMAGE), 0, 0, null);
      g.drawImage(readImage(abspath + parentFolder + "image/gameover.png"),
              (SCREEN_WIDTH - 853) / 2, (SCREEN_HEIGHT - 245) / 2, null);
    } else {
      g.drawImage(readImage(BACKGROUND_IMAGE), 0, 0, null);
      g.drawImage(readImage(TOOLBAR_IMAGE), 0, 0, null);

      for (int i = 0; i < ikan.getSize(); i++) {
        g.drawImage(readImage(ikan.getIdx(i).getImage()),
                (int) Math.floor(ikan.getIdx(i).getX()) - 80 / 2,
                (int) Math.floor(ikan.getIdx(i).getY() - 100) - 80 / 2, null);
      }

      for (int i = 0; i < makananikan.getSize(); i++) {
        g.drawImage(readImage(makananikan.getIdx(i).getImage()),
                (int) Math.floor(makananikan.getIdx(i).getX()) - 40 / 2,
                (int) Math.floor(makananikan.getIdx(i).getY()) - 100 - 40 / 2, null);
      }

      for (int i = 0; i < koin.getSize(); i++) {
        g.drawImage(readImage(koin.getIdx(i).getImage()),
                (int) Math.floor(koin.getIdx(i).getX()) - 65 / 2,
                (int) Math.floor(koin.getIdx(i).getY()) - 100 - 30 / 2, null);
      }

      if (player.getBanyaktelur() == 0) {
        g.drawImage(readImage(abspath + parentFolder + "image/telor1.png"),
                645 - 70 / 2, 45 - 64 / 2, null);
      } else if (player.getBanyaktelur() == 1) {
        g.drawImage(readImage(abspath + parentFolder + "image/telor2.png"),
                645 - 70 / 2, 45 - 64 / 2, null);
      } else if (player.getBanyaktelur() == 2) {
        g.drawImage(readImage(abspath + parentFolder + "image/telor3.png"),
                645 - 70 / 2, 45 - 64 / 2, null);
      }

      g.drawImage(readImage(siput.getImage()), (int) Math.floor(siput.getX()) - 100 / 2,
              (int) Math.floor(siput.getY()) - 100 - 141 / 2, null);

      g.drawString("100", 60, 93);
      g.drawString("200", 174, 93);
      g.drawString("500", 631, 93);

      if (kurangkoin) {
        g.setColor(Color.RED);
        g.drawString(player.getJumlahkoin() + "", 752, 93);
      } else {
        g.drawString(player.getJumlahkoin() + "", 752, 93);
      }

      g.drawImage(readImage(abspath + parentFolder + "image/save.png"),
              63 - 80 / 2, 580 - 80 / 2, null);
    }
  }
}
