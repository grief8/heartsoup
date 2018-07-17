package com.example.heartsoup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.spec.EncodedKeySpec;
import java.text.DateFormat;
import java.util.Date;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class article extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);


        final ImageButton i1 = (ImageButton) findViewById(R.id.imageButton);
        final TextView read = (TextView) findViewById(R.id.read);
        final ImageButton i2= (ImageButton) findViewById(R.id.imageButton4);


    i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = "最害人的，无非“现实点，这个社会就是这样”这句话\n" +
                        "有时候，我们\n" +
                        "难得鸡血、难得纯真、难得鲜活\n" +
                        "你猝不及防地一瓢冷水\n" +
                        "真的挺烦的\n" +
                        "\n" +
                        "你一定听人讲过一句话：“现实点，这个社会就是这样”。它超级影响你的心情。\n" +
                        "\n" +
                        "当你提出你的见解，说出你的想法的时候，有人对你说：哪有这么简单，你太单纯了！\n" +
                        "\n" +
                        "当你讲出你的目标，道出你的梦想的时候，有人会对你说：现实点吧，这个社会就这样！\n" +
                        "\n" +
                        "反正当你对生活还有一丝冲动，还有一点追求的时候，他们总会马不停蹄地跑过来，浇你一盆冷水。告诉你瞎折腾个什么劲，生活在这社会里，你躺着就够了。\n" +
                        "\n" +
                        "这是怎样的一个社会呢？它明明像一片森林，浩大、繁杂、多姿多彩。有的人却一叶障目，只看到眼前的尔虞尔诈，蝇营狗苟，就想当然地认为，这个世界也无非就这样。\n" +
                        "\n" +
                        "我在高中时，有一个“超然”的语文老师。他拥有满腹才华，却过得很“随性”。上课随便讲讲，作业一般批个“阅”。他认为一切皆空。阶级有阶级的宿命。穷人家的孩子有天生的瓶颈，瞎折腾个什么劲呢！\n" +
                        "\n" +
                        "他以前是我们县里有名的状元郎，被所有人寄予厚望。怎奈后来时运不济，在多次重要的人生转折点上，皆因背景和关系上差点火候，与想要的平台无缘。这样被生活不断捶打之后，他开始对这个社会失去信心。他相信阶级存在壁垒，认为人心险恶，自己也活得越来越麻木。\n" +
                        "\n" +
                        "他经常在我们的课堂上发牢骚，认为努力并没有什么鬼用。以一个教师的身份说出这些，我们那时是相当震惊的。\n" +
                        "\n" +
                        "不知道是因为说习惯了还是怎么的，或者想借机宣扬什么，在一次有教育组的领导旁听的公开课上，他竟然也絮絮叨叨地讲起了自己对这个社会的怨言。\n" +
                        "\n" +
                        "从那次课后，我们班就换了一个语文老师。官方的解释是他因身体不适，不适合继续进行教学工作。\n" +
                        "\n" +
                        "自那以后，我就再也没有见到他。但我见过很多像他这样的人。他们因为曾经看到过这个世界的假象，坚信挣扎无用。与其做无用功，不如当一个行尸走肉。他们浑身散发着颓废的气息，像丧失病毒一样，试图传染给身边的人。\n" +
                        "\n" +
                        "在你勾勒梦想的时候，他会凑过来瞟一眼，笑着说，别白日做梦了，生活哪有你想得这么容易。\n" +
                        "\n" +
                        "在你汗流浃背，为一个重要的offer拼搏的时候，他跑过来阴阳怪气地说，别幼稚了，这种事一般都是内定的了。劝你别费劲了。\n" +
                        "\n" +
                        "他们认为这个世界有问题，处处都充满了黑暗，活得愤世嫉俗。个人混得不如意，也与自身能力无关，全是这个社会的错。他们早已对命运妥协。还常常“好心”地提醒你，喜欢当你人生的导师。\n" +
                        "\n" +
                        "人生如果安于现状，像他们那样现实点，确实也就只能这样了。可是没有斗志的日子，跟咸鱼有什么区别。\n" +
                        "\n" +
                        "\n" +
                        "去年有一次我在地铁上遇到大学同学高凯。久别重逢，我们谈了很多。他问我是去哪，我说是去一个公司面试。\n" +
                        "\n" +
                        "当他听到公司的名字的时候，脸色一下子就变了。他以过来人的口吻劝我不要去，去也是浪费时间。他说他一个月前也曾去那家公司面试过。跟他一起去面试的竞争者是专科毕业，没有多少工作经验。最后却被录取了。据他了解，那个人是公司领导的侄子，所谓的面试都是走走过场。\n" +
                        "\n" +
                        "说完还不忘总结，一般好一点的工作都会这样，得靠关系才有机会，这个社会就是这样。\n" +
                        "\n" +
                        "听他一席话，我心凉半截。下地铁后，准备打道回府。但想着回去也没事干，抱着试试看的心态，还是去参加了面试。\n" +
                        "\n" +
                        "结果我竟然被顺利录取了。上班一个月后，我还受到了领导的嘉奖。他说，我改变了他对理工大毕业生的看法。我问他为何这样讲。他说，两个月前他也曾面试过一位理工大的学生，虽然工作经验丰富，但是对专业知识一窍不通，只会信口开河。还不如专科生。后来经过我的侧面了解得知，他说的这个理工大的学生就是我的同学高凯。而那专科生呢，我现在的同事，也并没有什么领导关系！\n" +
                        "\n" +
                        "有的人自身不努力，还总喜欢哀叹时运不济，觉得社会很黑暗，埋没了他的才华。于是放弃追求，还总喜欢劝别人现实点，打消别人的积极性。最害人的泼冷水，无非就是这种。\n" +
                        "\n" +
                        "\n" +
                        "人生像一头大象，我们都是摸黑前行者。有的人摸到了象粪，闻了闻，就觉得前方是臭的，社会是黑的，于是放弃了行进。他们还怂恿别人尽快止步。可是有的人明明摸到了象腿，不忘初心，坚持攀登，终于爬上了象背，驾驭了人生。\n" +
                        "\n" +
                        "大家都是盲人摸象，没有人能看透人生。生活总有不如意的地方，规则也会有漏洞，他们可能刚巧看到了这一点。但那不是社会的常态，更不是你放弃努力的借口。梦想在你的脚下。不要轻信他人口中的“现实”，现实其实并没有他们说得这么糟。";
                read.setText(r);

                i1.setVisibility(View.INVISIBLE);
                i2.setVisibility(View.INVISIBLE);
        }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent read = new Intent();
                read.setClass(article.this,read.class);
                startActivity(read);*/
               String s= "                        \"有一种我们很不耻的行为，叫“趁人之危”。\" +\n" +
                       "                       \"趁人危难之际，设置诱惑，让对方往里钻，满足私心，达成私欲。\" +\n" +
                       "                       \"比如说，赌场上，趁对方输红了眼，放出高利贷；\" +\n" +
                       "                       \"再比如说，趁杨白劳没钱还债，把喜儿给霸占了；\" +\n" +
                       "                       \"更普遍一点的，是N多的猥琐男，在女神失恋之际，借着安慰之名，顺便把她的身体也安慰了……\" +\n" +
                       "                       \"诸如此类，说道德化一点：不是君子所为。\" +\n" +
                       "                       \"说得市井一点：你不干人事儿！\"+\n" +
                       "                       \"但趁人之危者，何以屡屡得手？换了一个场景，一种境地，一种心情，同样的伎俩，为什么却不奏效了？\" +\n" +
                       "                       \"心理学家通过大量的实验，证明：生活越困窘，情绪越低落，越让人难以抵抗诱惑。\" +\n" +
                       "                       \"也就是说，当你坠入低谷——现实的、情绪的——你面前的所有诱惑，都会变得更有诱惑力。\" +\n" +
                       "                       \"经济危机来临时，人们会更想购物；\" +\n" +
                       "                       \"工作压力大，会让你吃得更多；\" +\n" +
                       "                       \"拖延时间越长，会更加难以集中注意力；\" +\n" +
                       "                       \"令人恐惧的吸烟警示，会让烟民更渴望香烟；\" +\n" +
                       "                       \"看完《死神来了》等恐怖片后，人们会不自觉地，花上三倍的价钱，买上自己根本不需要的东西。\" +\n" +
                       "                       \"电击实验小白鼠时，他们会疯狂地渴望糖类、酒精、海洛因；\" +\n" +
                       "                       \"而在人类世界里，现实世界的压力，则会让戒烟、戒酒、戒毒、节食的人，更容易重蹈覆辙\"+\n" +
                       "                       \"想得到快乐，是一种自然的生存机制。\" +\n" +
                       "                       \"自我救援也很正常。\" +\n" +
                       "                       \"但问题是，大多数释放压力的方法，会让我们更有压力。\" +\n" +
                       "                       \"美国心理学家做过一次统计，发现最常用的解压方法——吃东西、喝酒、看电视、上网、购物、玩游戏——往往是最无效的方法。\" +\n" +
                       "                       \"比如，通过暴饮暴食来解压的人里，只有16%认为，这种方法有效。\" +\n" +
                       "                       \"而在另一项调查里，女性感到抑郁时，去吃大量巧克力，结果却是带来更大的罪恶感。\" +\n" +
                       "                       \"还有一项调查则发现，失意者购物更多，看到忽然暴减的银行存款，会带来更剧烈的自我批评。\" +\n" +
                       "                       \"罪恶感和自我批评，又会让我们情绪更低落。\" +\n" +
                       "                       \"于是，陷入恶性循环。\" +\n" +
                       "                       \"烟民的烟瘾越来越凶；暴饮暴食者吃进去更多的食物；购物狂会刷爆更多信用卡；拖延症患者浪费更多的时间。\"+\n" +
                       "                       \"那，我们就没办法了吗？\" +\n" +
                       "                       \"当然不是。\" +\n" +
                       "                       \"我们还有科学的解压方法。\" +\n" +
                       "                       \"首先，你要记得：当我们陷入“人之危”，我们需要的，不是释放多巴胺，而是增加催产素。\" +\n" +
                       "                       \"多巴胺让我们兴奋，却不是拯救良方。\" +\n" +
                       "                       \"只有血清素、y－氨基丁酸和一些让人愉悦的催产素，才能有效减少压力荷尔蒙，让大脑解脱，产生有治愈效果的放松反应。\" +\n" +
                       "                       \"那么，做什么事情才能产生这些元素呢？\" +\n" +
                       "                       \"心理学家发现，瑜珈、冥想、散步、祈祷及参加宗教活动、洗澡、阅读、听音乐、与家人朋友相处、按摩、画画、培养有创意的爱好……都能增加这些化学物质。\" +\n" +
                       "                       \"虽然，相较暴食饮酒购物而言，这种方法产生的“快乐药”，剂量小、见效慢、程度轻微，不会让人立即好转，但是，它才是唯一有效的解压之策。\"+\n" +
                       "                       \"除此之外，还要学着接纳你的过错（引起你情绪低落的）。\" +\n" +
                       "                       \"众多研究显示，自我攻击，不会增加我们的力量，而是会削弱我们的意志。\" +\n" +
                       "                       \"比如，一个实验是这样的：\" +\n" +
                       "                       \"女士们在实验室里，被要求吃一个甜甜圈，并喝一大杯水，产生强烈的饱腹感。\" +\n" +
                       "                       \"接下来，她要完成一份答卷。\" +\n" +
                       "                       \"如果她在答卷中，说自己很有罪恶感，那么，她会在接下来的巧克力、爆米花、馅饼诱惑面前，会更加难以自制。\" +\n" +
                       "                       \"她会比无罪恶感的女士，吃掉多出一倍多的食物。\" +\n" +
                       "                       \"因为：\" +\n" +
                       "                       \"“我的减肥计划已经失败了，那我再吃点又有什么关系呢？”\" +\n" +
                       "                       \"由此可见，自我接纳比自我打击，对意志的恢复，要有效得多。\" +\n" +
                       "                       \"也因此，凯利·麦格尼格尔说：情绪低落会使人屈服于诱惑，摆脱罪恶感会让你变得更强大。\" +\n" +
                       "                       \"只有有效地解压，真正地接纳自己，我们才能更有效地，恢复真正的乐观，凝聚自己的意志力，在各种人生低谷中，依然眼睛明亮、骨骼坚硬、笑容迷人，继尔找到那条路，带领自己逃离困境，继续向前……";
               read.setText(s);
               i1.setVisibility(View.INVISIBLE);
               i2.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        ImageButton i1 = (ImageButton) findViewById(R.id.imageButton);
        ImageButton i2= (ImageButton) findViewById(R.id.imageButton4);
        TextView read = (TextView) findViewById(R.id.read);
        i1.setVisibility(View.VISIBLE);
        i2.setVisibility(View.VISIBLE);
        read.setText("");
    }

    public String readRaw(int read){
        try {
            Resources inr = getResources();
            InputStream in = inr.openRawResource(read);
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead = in.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, hasRead));
            }
            in.close();
            String utf8 = new String(sb.toString().getBytes("UTF-8"));
            String gbk = new String(utf8.getBytes("GBK"));
            return  gbk;
//            return new String(sb.toString().getBytes("iso8859-1"),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
