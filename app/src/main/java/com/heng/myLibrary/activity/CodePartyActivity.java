package com.heng.myLibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.heng.myLibrary.R;
import com.heng.myLibrary.adapter.CodePartyLvAdapter;
import com.heng.myLibrary.database.bean.CodePartyItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/12/21 22:29
 * <p>
 * 积分活动界面
 */

public class CodePartyActivity extends AppCompatActivity {

    ListView codePartyLv;
    ImageView codePartyBack;
    List<CodePartyItemBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_party_activity);

        codePartyLv = findViewById(R.id.code_party_lv);
        codePartyBack = findViewById(R.id.code_party_back);
        codePartyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        showCodeParties();
    }

    private void showCodeParties() {
        mDatas = new ArrayList<>();
        CodePartyItemBean codePartyItemBean1 = new CodePartyItemBean("讲座:", "参考文献的正确引用",
                "        图书馆（档案馆）第十五届服务月系列活动之一。学术诚信是科学精神的基底，大学生在学术生涯的开端就要爱惜自己的学术羽毛，做一个坚定的学术诚信捍卫者。本讲座从违反学术道德的案例出发，讲述学术诚信的内涵，并重点讲解如何正确标引文后参考文献。");
        CodePartyItemBean codePartyItemBean2 = new CodePartyItemBean("校园文化:", "“金海艺韵”艺术沙龙第三期",
                "        话剧是一种主要运用对话和动作表情来传情达意的戏剧样式，是一门涵盖剧本创作、导演、表演、舞美、灯光、评论等都缺一不可的综合艺术。本次沙龙活动通过直观的话剧形式，简单明了的对话和巧妙的舞台设计，让人们感受在戏剧中人与人之间最真实纯粹的情感传达。");
        CodePartyItemBean codePartyItemBean3 = new CodePartyItemBean("讲座:", "数理讲堂”第十三期",
                "        复双曲空间是非常重要的秩一对称空间和特殊的凯莱流形，它的研究与离散群几何、复几何、Heisenberg群几何、流形的几何结构、非算术格的构造等重要问题紧密相关。本报告主要介绍复双曲几何的背景知识与复双曲三角群的研究进展，然后简要介绍通过研究一些复双曲三角群的几何，从而给出一些非紧双曲三维流形的球面CR单值化。");
        CodePartyItemBean codePartyItemBean4 = new CodePartyItemBean("校园文化:", "国家级非遗上海绒绣展",
                "        十九届六中全会指出，要坚持把马克思主义基本原理同中华优秀传统文化相结合。上海绒绣，是极具海派文化特色的民间传统美术，国家级非物质文化遗产之一，以其形象逼真、色彩浓郁、层次清晰、立体感强的“海派”艺术风格而独树一帜，其优秀作品收藏展示于人民大会堂等国家重要场馆之中。为全面贯彻落实党的十九届六中全会精神，在青年学子中更好地传承发展中华优秀传统文化，本展览将选取上海绒绣中以“中国世界文化遗产”“祖国风光”“城市风景”“中国动物”等为主题创作的作品进行集中展示，重点展示中国锦绣河山和盛世图景，传播中国传统文化、红色文化、海派文化，并在高校掀起保护和传承非遗的热潮，激活非遗魅力，厚植大学生爱国主义情怀，提升高校师生艺术审美水平。");
        CodePartyItemBean codePartyItemBean5 = new CodePartyItemBean("讲座:", "二维环面上的五次薛定谔方程拟周期解的构造 ",
                "        本报告主要介绍一个无穷维哈密顿系统的KAM理论。作为一个应用报告者将给出一类二维环面上的五次薛定谔方程拟周期解的构造。");

        mDatas.add(codePartyItemBean1);
        mDatas.add(codePartyItemBean2);
        mDatas.add(codePartyItemBean3);
        mDatas.add(codePartyItemBean4);
        mDatas.add(codePartyItemBean5);
        CodePartyLvAdapter adapter = new CodePartyLvAdapter(getBaseContext(), mDatas);
        codePartyLv.setAdapter(adapter);
    }
}