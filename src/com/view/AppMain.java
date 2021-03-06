package com.view;
import com.Main;
import com.control.MenuBarEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import com.view.dataplay;
import com.view.JFSwingDynamicChart;
import com.view.findview;
public class AppMain extends JFrame {
    private static final long serialVersionUID = -8348833890456775157L;
    JPanel contentPane;
    BorderLayout borderLayout1 = new BorderLayout();
    JDesktopPane desktop = new JDesktopPane();
    JDesktopPane destest = new JDesktopPane();
    MenuBarEvent _MenuBarEvent = new MenuBarEvent(); // 自定义事件类处理
    JMenuBar jMenuBarMain = new JMenuBar(); // 定义界面中的主菜单控件
    JToolBar jToolBarMain = new JToolBar(); // 定义界面中的工具栏控件
    CardLayout card = new CardLayout();
    public JPanel Cpanel = new JPanel(card);
    JPanel Mainpanel = new JPanel();    //欢迎界面之后的界面 实现数据显示
    String s_com,s_pinlv,snum;   //接收串口配置的字符串
    public String s_place,s_temp,s_shi,s_gas,s_other,s_name; //接收实验条件的信息
    int tdnum;    //定义通道数量
    JFSwingDynamicChart Jchart ;
    JPanel xinxipanel ;  //查看实验信息的界面
    JButton[] jb = new JButton[7];  //tollbar 中的按钮 在这里定义 是为了进行之后更改权限

   public void APPinit(){
       try {
           _MenuBarEvent.setAPP(this);  //传 面板
           _MenuBarEvent.setFrame(this);//传 界面
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           BuildMenuBar();
           BuildToolBar();
           jbInit();
         //  test();
         //  loadBackgroundImage();
           cardpanel_firinit();


       } catch (Exception exception) {
           exception.printStackTrace();
       }
   }

    /**
     * Component initialization.
     *
     * @throws Exception
     */
    private void jbInit() throws Exception {
        jToolBarMain.setFloatable(false);
        this.setJMenuBar(jMenuBarMain);
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(borderLayout1);
        setSize(new Dimension(1300, 800));
        setTitle("多参量数据融合处理平台");
      //  cardpanel.setLayout(new CardLayout());
        contentPane.add(Cpanel, BorderLayout.CENTER);
        contentPane.add(jToolBarMain, BorderLayout.NORTH);
       // _MenuBarEvent.setDeskTop(desktop);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         Dimension frameSize = getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setVisible(true);


    }
      private void cardpanel_firinit(){
        /*
        * 初始界面
        * */
          _MenuBarEvent.setDeskTop(desktop);
          ImageIcon icon = new ImageIcon(this.getClass().getResource("/picture/main.jpg"));
          JLabel jl = new JLabel(icon);
          jl.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
          desktop.add(jl, new Integer(Integer.MIN_VALUE));
          Cpanel.add(desktop,"welcome");
          changeviewfirst();
          Cpanel.setVisible(true);


      }
      /*
      * 数据传送界面初始化
      * */
        public void cardpanel_secinit(){
            /*
             * 定义主界面 数据显示部分；
             * 在此界面进行dataplay 的实例对象data_view，调用函数，实现dtata_view的界面初始化和chart对象
             * 在这个类中向data_view传送事件监听，data_view向事件监听传送chart对象
             * 在data_view中进行处理
             *向事件监听传送界面
             * */

            dataplay data_view = new dataplay();
            data_view.setAPPpanel(this);
            data_view.setsaw_massage(s_place,s_temp,s_shi,s_gas,s_other,s_name);
            data_view.viewinit();//初始化，实现两个界面（data view和contr view）的初始化和chart对象
            data_view.setMenuEvent(_MenuBarEvent);  //向data_view传送事件监听，让他获取事件监听之后向事件监听传送chart实例
            Mainpanel = data_view.getMainpanel();
            Cpanel.add(Mainpanel,"datapanel");
            _MenuBarEvent.setDataview(data_view);  //传送数据传送界面
        }
        /*
        * 第三个界面初始化
        * */
        public void thirdvie_ini(){
             findview findv = new findview();
             findv.mainpanelinit();  //初始化
             xinxipanel = findv.getMainpanel();  //获取界面
            Cpanel.add(xinxipanel,"findview");
        }
        /*转换到第三个界面
        * */
        public void changeviewthird(){
            thirdvie_ini();
            card.show(Cpanel,"findview");  //显示界面
        }
     /*
     * 使用此函数，实现主界面进入通道显示界面,进行转换
     * */
      public void changeview(){
          cardpanel_secinit();
          //card.previous(Cpanel);
          card.show(Cpanel,"datapanel");
      }
      /*
      * 使用此函数返回主界面，并实现数据重置
      * */
      public void changeviewfirst(){
          card.first(Cpanel);
          //Main.ifallo = false;
          //Main.ifnew = false;
      }
    /*
     Menu bar
     定义界面
*/
    private void BuildMenuBar() {
        JMenu[] _jMenu = { new JMenu("文件 "), new JMenu("编辑 "), new JMenu("操作 "), new JMenu("配置 "),new JMenu("帮助") };
        JMenuItem[] _jMenuItem0 = { new JMenuItem("浏览数据"), new JMenuItem("本地数据"), new JMenuItem("退出系统") }; //本地数据打开本地的文件夹，初始化一个文件夹
        String[] _jMenuItem0Name = { "saw_find", "saw_localdate", "saw_exit" };
        JMenuItem[] _jMenuItem1 = { new JMenuItem("删除实验") };
        String[] _jMenuItem1Name = { "saw_delete" };
        JMenuItem[] _jMenuItem2 = { new JMenuItem("新建实验"), new JMenuItem("开始实验"), new JMenuItem("暂停实验"),new JMenuItem("停止实验") };
        String[] _jMenuItem2Name = { "saw_new", "saw_start", "saw_suspend","saw_end" };
        JMenuItem[] _jMenuItem3 = { new JMenuItem("串口配置") };
        String[] _jMenuItem3Name = { "saw_allocation" };
        JMenuItem[] _jMenuItem4 = {new JMenuItem("关于")};
        String[] _jMenuItemName4 = {"saw_about"};
        Font _MenuItemFont = new Font("宋体", 0, 12);
        for (int i = 0; i < _jMenu.length; i++) {
            _jMenu[i].setFont(_MenuItemFont);
            jMenuBarMain.add(_jMenu[i]);
        }
        for (int j = 0; j < _jMenuItem0.length; j++) {
            _jMenuItem0[j].setFont(_MenuItemFont);
            final String EventName1 = _jMenuItem0Name[j];
            _jMenuItem0[j].addActionListener(_MenuBarEvent);
            _jMenuItem0[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    _MenuBarEvent.setEventName(EventName1);
                }
            });
            _jMenu[0].add(_jMenuItem0[j]);
           // if (j == 0) {
            //    _jMenu[0].addSeparator();   加下划线
         //   }
        }
        for (int j = 0; j < _jMenuItem1.length; j++) {
            _jMenuItem1[j].setFont(_MenuItemFont);
            final String EventName1 = _jMenuItem1Name[j];
            _jMenuItem1[j].addActionListener(_MenuBarEvent);
            _jMenuItem1[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    _MenuBarEvent.setEventName(EventName1);
                }
            });
            _jMenu[1].add(_jMenuItem1[j]);
           /* if (j == 1) {
                _jMenu[1].addSeparator();
            }*/
        }
        for (int j = 0; j < _jMenuItem2.length; j++) {
            _jMenuItem2[j].setFont(_MenuItemFont);
            final String EventName2 = _jMenuItem2Name[j];
            _jMenuItem2[j].addActionListener(_MenuBarEvent);
            _jMenuItem2[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    _MenuBarEvent.setEventName(EventName2);
                }
            });
            _jMenu[2].add(_jMenuItem2[j]);
            if ((j == 0)) {
           /*     _jMenu[2].addSeparator();
            }*/
            }
        }
        for (int j = 0; j < _jMenuItem3.length; j++) {
            _jMenuItem3[j].setFont(_MenuItemFont);
            final String EventName3 = _jMenuItem3Name[j];
            _jMenuItem3[j].addActionListener(_MenuBarEvent);
            _jMenuItem3[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    _MenuBarEvent.setEventName(EventName3);
                }
            });
            _jMenu[3].add(_jMenuItem3[j]);
           /* if (j == 0) {
                _jMenu[3].addSeparator();
            }*/
        }

        for (int j = 0; j < _jMenuItem4.length; j++) {
            _jMenuItem4[j].setFont(_MenuItemFont);
            final String EventName4 = _jMenuItemName4[j];
            _jMenuItem4[j].addActionListener(_MenuBarEvent);
            _jMenuItem4[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    _MenuBarEvent.setEventName(EventName4);
                }
            });
            _jMenu[4].add(_jMenuItem4[j]);
           /* if (j == 0) {
                _jMenu[3].addSeparator();
            }*/
        }
    }
    /*
      toolbar
      设置
    */

    private void BuildToolBar() {
        String ImageName[] = { "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "8.GIF", };
        String TipString[] = { "新建实验", "开始实验", "暂停实验", "停止实验", "配置", "浏览数据", "退出系统" };
        String ComandString[] = { "saw_new", "saw_start", "saw_suspend", "saw_end","saw_allocation" ,"saw_find", "saw_exit"};
        //
        for (int i = 0; i < ComandString.length; i++) {
           jb[i] = new JButton();
            ImageIcon image = new ImageIcon(this.getClass().getResource("/picture/" + ImageName[i]));
            jb[i].setIcon(image);
            jb[i].setToolTipText(TipString[i]);
            jb[i].setActionCommand(ComandString[i]);
            jb[i].addActionListener(_MenuBarEvent);
            jToolBarMain.add(jb[i]);
        }
        //设定哪些 按钮不可使用
        changebuttonvisauble(new int[]{0,1,2,3});
    }

    /*
     * 设置左边的界面 作参考
     * */
  /*  private void test() {
        JDesktopPane test_panel = new JDesktopPane();
        Dimension frameSize = contentPane.getSize();
        test_panel.setPreferredSize(new Dimension(100, frameSize.height));  //只能用这个改
        test_panel.setBackground(Color.YELLOW);
        contentPane.add(test_panel,BorderLayout.EAST);

    }*/
   /* private void setvie(){
        _MenuBarEvent.setAPP(this);
    }*/
    public Frame getframe() {
        Frame f = (Frame)this;
        return f;
    }
  /*
  * 接收串口配置的信息
  * */
    public void setS_com(String S1){
        s_com = S1;
    }
    public void setS_pinlv(String s2){
        s_pinlv = s2;
    }
    public void setSnum(String s3){
        snum = s3;
    }
   /*
   * 获取实验条件信息
   * */
    public void setgas_message(String s1,String s2,String s3,String s4,String s5,String s6){
        s_place = s1;
        s_temp = s2;
        s_shi = s3;
        s_gas = s4;
        s_other = s5;
        s_name = s6;
    }
    /*
    * 传送 串口信息
    * */
    public String getS_com(){ return s_com ;}
    public String getS_pinlv(){return s_pinlv ;}
    public String getSnum(){ return snum; }
    /*
    * 传送实验信息
    * */
    public String getS_place(){return s_place;}   //传回地点信息
    public String getS_temp(){return s_temp;}   //传回实验温度
    public String getS_shi(){return s_shi;}    //传回实验湿度
    public String getS_gas(){return s_gas;}     //传回实验气体
    public String getS_other(){return s_other;}
    public String getS_name(){return s_name;}
     /*
     * 设置哪些按钮可见那些不可见
     * */
     public void changebuttonvisauble(int arr[]){
         if(arr == null){  //这些按钮都用
             for(int i=0;i<7;i++){
                 jb[i].setEnabled(true);
             }
         }
         else {
             for(int i=0;i<7;i++){
                 boolean f = true;
                 //判断哪些按钮可见
                 for(int j=0;j<arr.length;j++){
                     //此按钮是不可使用
                     if(i == arr[j]){
                         f = false;
                         break;
                     }
                 }
                 jb[i].setEnabled(f);
             }
         }

     }
}
