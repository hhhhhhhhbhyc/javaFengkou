package com.example.fengkou_2.houtaiBag.Controller;

import com.example.fengkou_2.IndexBag.Model.HezuohuobanModel;
import com.example.fengkou_2.IndexBag.Model.Zhangjie;
import com.example.fengkou_2.IndexBag.mapper.ZhangjieMapper;
import com.example.fengkou_2.LoginRegoster.Model.User;
import com.example.fengkou_2.LuntanBag.Mapper.LuntanMapper;
import com.example.fengkou_2.PersonalBag.Mapper.PersonalAccuracyMapper;
import com.example.fengkou_2.TiKuBag.Mapper.TiKuMapper;
import com.example.fengkou_2.TiKuBag.Model.BoolModel;
import com.example.fengkou_2.TiKuBag.Model.ChoiceModel;
import com.example.fengkou_2.TiKuBag.Model.ResultfenxiModel;
import com.example.fengkou_2.TiKuBag.Model.TiankongModel;
import com.example.fengkou_2.Util.XiniuUtil;
import com.example.fengkou_2.ZhishikuBag.Mapper.ZhishiMapper;
import com.example.fengkou_2.ZhishikuBag.Model.NonStructModel;
import com.example.fengkou_2.houtaiBag.Mapper.ZhangjiehoutaiMapper;
import com.example.fengkou_2.houtaiBag.Model.LuntanOriModel;
import com.example.fengkou_2.houtaiBag.Model.TikuOriModel;
import com.example.fengkou_2.houtaiBag.Model.ZhishiOriModel;
import com.example.fengkou_2.houtaiBag.Model.ZhishitiaoOriModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class HoutaiMainController {
    @Autowired
    private ZhangjieMapper zhangjieMapper;
    @Autowired
    private ZhangjiehoutaiMapper zhangjiehoutaiMapper;
    @Autowired
    private PersonalAccuracyMapper personalAccuracyMapper;
    @Autowired
    private LuntanMapper luntanMapper;
    @Autowired
    private ZhishiMapper zhishiMapper;
    @Autowired
    private TiKuMapper tiKuMapper;


    //??????????????????

    /**
     * ???
     *
     * @param model
     * @return
     */
    @GetMapping("/houtai/main")
    public String getHoutai(Model model) {
        List<Zhangjie> zhangjie = zhangjieMapper.selectallFromZhangjie();
        model.addAttribute("zhangjie", zhangjie);
        return "houtai-main";
    }

    /**
     * ???
     *
     * @param model
     * @param zhangjieid
     * @return
     */
    @GetMapping("/houtai/delzhang/{zhangjieid}")
    public String delzhang(Model model,
                           @PathVariable("zhangjieid") int zhangjieid) {
        zhangjiehoutaiMapper.delZhang(zhangjieid);
        return "redirect:/houtai/main";
    }

    /**
     * ??????
     *
     * @param model
     * @param zhangjieid
     * @return
     */
    @RequestMapping("/houtai/updatezhang/{zhangjieid}")
    public String updatezhang(Model model,
                              @PathVariable("zhangjieid") int zhangjieid) {
        Zhangjie zhangjie = zhangjiehoutaiMapper.selectZhangjie(zhangjieid);
        model.addAttribute("zhangjie", zhangjie);
        return "houtai-update";
    }

    /**
     * ????????????
     *
     * @param model
     * @param zhangjieid
     * @return
     */
    @RequestMapping("/houtai/updatezhangfin/{zhangjieid}")
    public String updatezhangfin(Model model,
                                 @PathVariable("zhangjieid") int zhangjieid,
                                 @RequestParam("zhang") int zhang,
                                 @RequestParam("jie") int jie,
                                 @RequestParam("miaoshu") String miaoshu,
                                 @RequestPart("path") MultipartFile path,
                                 @RequestPart("mengmianpath") MultipartFile mengmianpath
    ) {
        String fileName = path.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName = date + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        String url = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName;

        String fileName_2 = mengmianpath.getOriginalFilename();
        String date_2 = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName_2 = date_2 + System.currentTimeMillis() + fileName_2.substring(fileName_2.lastIndexOf("."));
        String url_2 = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName_2;

        try {
            XiniuUtil.upload2Qiniu2(path.getBytes(), fileName);
            XiniuUtil.upload2Qiniu2(mengmianpath.getBytes(), fileName_2);
            zhangjiehoutaiMapper.updateZhang(zhang, jie, miaoshu, url, url_2, zhangjieid);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/houtai/main";
    }

    /**
     * ??????
     *
     * @param model
     * @return
     */
    @RequestMapping("/houtai/addzhang")
    public String addzhang(Model model) {
        return "houtai-add";
    }

    /**
     * ????????????
     *
     * @param model
     * @param zhang
     * @param jie
     * @param miaoshu
     * @param path
     * @param mengmianpath
     * @return
     */
    @RequestMapping("/houtai/addzhangfin")
    public String addzhangfin(Model model,
                              @RequestParam("zhang") int zhang,
                              @RequestParam("jie") int jie,
                              @RequestParam("miaoshu") String miaoshu,
                              @RequestPart("path") MultipartFile path,
                              @RequestPart("mengmianpath") MultipartFile mengmianpath
    ) {
        String fileName = path.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName = date + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        String url = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName;

        String fileName_2 = mengmianpath.getOriginalFilename();
        String date_2 = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName_2 = date_2 + System.currentTimeMillis() + fileName_2.substring(fileName_2.lastIndexOf("."));
        String url_2 = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName_2;

        try {
            XiniuUtil.upload2Qiniu2(path.getBytes(), fileName);
            XiniuUtil.upload2Qiniu2(mengmianpath.getBytes(), fileName_2);
            zhangjiehoutaiMapper.addZhang(zhang, jie, miaoshu, url, url_2);
            System.out.println(zhang + "" + jie + "" + url);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/houtai/main";
    }


    //---------------------------------------------------------------
    @RequestMapping("/houtai/tiku")
    public String gettiku(Model model) {
        List<TikuOriModel> tiku = zhangjiehoutaiMapper.selectAlltiku();
        model.addAttribute("tiku", tiku);

        return "houtai-tiku-main";
    }

    /**
     * ???
     *
     * @param model
     * @param tid
     * @return
     */
    @GetMapping("/houtai/deltiku/{tid}")
    public String deltiku(Model model,
                          @PathVariable("tid") int tid) {
        zhangjiehoutaiMapper.deltiku(tid);
        return "redirect:/houtai/tiku";
    }

    /**
     * ??????
     *
     * @param model
     * @return
     */
    @RequestMapping("/houtai/addtiku")
    public String addtiku(Model model) {
        return "houtai-tiku-add";
    }

    /**
     * ????????????
     *
     * @param model
     * @param tixin
     * @param zhangjieid
     * @param nandu
     * @param passratio
     * @param tiganjianchen
     * @return
     */
    @RequestMapping("/houtai/addtikufin")
    public String addtikufin(Model model,
                             @RequestParam("tixin") int tixin,
                             @RequestParam("zhangjieid") int zhangjieid,
                             @RequestParam("nandu") int nandu,
                             @RequestParam("passratio") float passratio,
                             @RequestParam("tiganjianchen") String tiganjianchen
    ) {
        zhangjiehoutaiMapper.addtiku(tixin, zhangjieid, nandu, passratio, tiganjianchen);
        return "redirect:/houtai/tiku";
    }


    @RequestMapping("/houtai/updatetiku/{tid}")
    public String updatetiku(Model model,
                             @PathVariable("tid") int tid) {
        TikuOriModel tikuOriModel = zhangjiehoutaiMapper.selecttiku(tid);
        model.addAttribute("tiku", tikuOriModel);
        return "houtai-tiku-update";
    }


    @RequestMapping("/houtai/updatetikufin/{tid}")
    public String updatetikufin(Model model,
                                @PathVariable("tid") int tid,
                                @RequestParam("zhangjieid") int zhangjieid,
                                @RequestParam("nandu") int nandu,
                                @RequestParam("passratio") Float passratio,
                                @RequestParam("tiganjianchen") String tiganjianchen,
                                @RequestParam("tixin") int tixin
    ) {

        zhangjiehoutaiMapper.updatetiku(tixin, zhangjieid, nandu, passratio, tiganjianchen, tid);

        return "redirect:/houtai/tiku";
    }

    //-----------------------------------------------------------------------
    @RequestMapping("/houtai/zhishiku")
    public String getzhishiku(Model model) {
        List<ZhishiOriModel> zhishiku = zhangjiehoutaiMapper.selectAllzhishiku();
        model.addAttribute("zhishiku", zhishiku);

        return "houtai-zhishiku-main";
    }

    /**
     * ???
     *
     * @param model
     * @param zid
     * @return
     */
    @GetMapping("/houtai/delzhishiku/{zid}")
    public String delzhishiku(Model model,
                              @PathVariable("zid") int zid) {
        zhangjiehoutaiMapper.delzhishiku(zid);
        return "redirect:/houtai/zhishiku";
    }

    /**
     * ??????
     *
     * @param model
     * @return
     */
    @RequestMapping("/houtai/addzhishiku")
    public String addzhishilu(Model model) {
        return "houtai-zhishiku-add";
    }

    /**
     * ????????????
     *
     * @param model
     * @param zhishidian
     * @param zhangjieid
     * @return
     */
    @RequestMapping("/houtai/addzhishikufin")
    public String addzhishikufin(Model model,
                                 @RequestParam("zhishidian") String zhishidian,
                                 @RequestParam("zhangjieid") int zhangjieid

    ) {
        zhangjiehoutaiMapper.addzhishiku(zhishidian, zhangjieid);
        return "redirect:/houtai/zhishiku";
    }


    @RequestMapping("/houtai/updatezhishiku/{zid}")
    public String updatezhishiku(Model model,
                                 @PathVariable("zid") int zid) {
        ZhishiOriModel zhishiOriModel = zhangjiehoutaiMapper.selectzhishiku(zid);
        model.addAttribute("zhishiku", zhishiOriModel);
        return "houtai-zhishiku-update";
    }


    @RequestMapping("/houtai/updatezhishikufin/{zid}")
    public String updatezhishukufin(Model model,
                                    @PathVariable("zid") int zid,
                                    @RequestParam("zhangjieid") int zhangjieid,
                                    @RequestParam("zhishidian") String zhishidian
    ) {

        zhangjiehoutaiMapper.updatezhishiku(zhishidian, zhangjieid, zid);
        return "redirect:/houtai/zhishiku";
    }

    //------------------------------------------------------------
    @RequestMapping("/houtai/luntan")
    public String getluntan(Model model) {
        List<LuntanOriModel> luntan = zhangjiehoutaiMapper.selectAllluntan();
        model.addAttribute("luntan", luntan);

        return "houtai-luntan-main";
    }

    /**
     * ???
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/houtai/delluntan/{id}")
    public String delluntan(Model model,
                            @PathVariable("id") int id) {

        String auther = luntanMapper.selectAuther(id);
        System.out.println(auther);

        Date date = new Date();
        Timestamp sqlDate_ori = new Timestamp(date.getTime());
        personalAccuracyMapper.insertletter("???????????????", "?????????????????????????????????????????????????????????", sqlDate_ori, auther);

        zhangjiehoutaiMapper.delluntan(id);
        return "redirect:/houtai/luntan";
    }

    //-----------------------------------------------------------
    @RequestMapping("/houtai/nonzhishi")
    public String getnonzhishi(Model model) {
        List<NonStructModel> nonzhishi = zhangjiehoutaiMapper.selectAllnonzhishi();
        model.addAttribute("nonzhishi", nonzhishi);

        return "houtai-nonzhishi-main";
    }

    /**
     * ???
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/houtai/delnonzhishi/{id}")
    public String delnonzhishi(Model model,
                               @PathVariable("id") int id) {

        String auther = zhishiMapper.selectnonstructbyUser(id);

        Date date = new Date();
        Timestamp sqlDate_ori = new Timestamp(date.getTime());
        personalAccuracyMapper.insertletter("?????????????????????", "???????????????????????????????????????????????????????????????", sqlDate_ori, auther);

        zhangjiehoutaiMapper.delnonzhishi(id);
        return "redirect:/houtai/nonzhishi";
    }

    //------------------------------------------------------------------
    @RequestMapping("/houtai/hzhb")
    public String gethzhb(Model model) {
        List<HezuohuobanModel> hzhb = zhangjiehoutaiMapper.selectAllhzhb();
        model.addAttribute("hzhb", hzhb);
        return "houtai-hzhb-main";
    }

    /**
     * ???
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/houtai/delhzhb/{id}")
    public String delhzhb(Model model,
                          @PathVariable("id") int id) {
        zhangjiehoutaiMapper.delhzhb(id);
        return "redirect:/houtai/hzhb";
    }

    /**
     * ??????
     *
     * @param model
     * @return
     */
    @RequestMapping("/houtai/addhzhb")
    public String addhzhb(Model model) {
        return "houtai-hzhb-add";
    }


    @RequestMapping("/houtai/updatehzhb/{id}")
    public String updatehzhb(Model model,
                             @PathVariable("id") int id) {
        HezuohuobanModel hzhb = zhangjiehoutaiMapper.selecthzhb(id);
        model.addAttribute("hzhb", hzhb);
        return "houtai-hzhb-update";
    }


    /**
     * ????????????
     *
     * @param model
     * @param title
     * @param wailian
     * @param pic
     * @return
     */
    @RequestMapping("/houtai/addhzhbfin")
    public String addhzhbfin(Model model,
                             @RequestParam("title") String title,
                             @RequestParam("wailian") String wailian,
                             @RequestPart("pic") MultipartFile pic
    ) {
        String fileName = pic.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName = date + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        String url = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName;

        try {
            XiniuUtil.upload2Qiniu2(pic.getBytes(), fileName);
            zhangjiehoutaiMapper.addhzhb(url, title, wailian);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/houtai/hzhb";
    }


    @RequestMapping("/houtai/updatehzhbfin/{id}")
    public String updatehzhbfin(Model model,
                                @PathVariable("id") int id,
                                @RequestParam("title") String title,
                                @RequestParam("wailian") String wailian,
                                @RequestPart("pic") MultipartFile pic
    ) {
        String fileName = pic.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName = date + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        String url = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName;

        try {
            XiniuUtil.upload2Qiniu2(pic.getBytes(), fileName);
            zhangjiehoutaiMapper.updatehzhb(url, title, wailian, id);
            System.out.println(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/houtai/hzhb";
    }

    //------------------------------------------------------------
    @RequestMapping("/houtai/update/{id}")
    public String updatemain(Model model,
                             @PathVariable("id") int id) {
        int panduan = tiKuMapper.judgeTixin(id);
        //??????
        if (panduan == 1) {

            ChoiceModel choiceModel = tiKuMapper.selectChoicebyTikuid(id);
            System.out.println(choiceModel);
            model.addAttribute("tiku", choiceModel);

            return "houtai-xuanzeOri-update";
        }
        //??????
        if (panduan == 0) {

            BoolModel boolModel = tiKuMapper.selectBoolbyTikuid(id);
            model.addAttribute("tiku", boolModel);

            return "houtai-panduanOri-update";
        }

        //??????
        if (panduan == 2) {

            TiankongModel tiankongModel = tiKuMapper.selectTiankongbyTikuid(id);
            model.addAttribute("tiku", tiankongModel);
            return "houtai-tiankongOri-update";
        }

        //??????
        if (panduan == 3) {
            ResultfenxiModel fenxiModel = tiKuMapper.selectFenxibyTikuid(id);
            model.addAttribute("tiku", fenxiModel);
            return "houtai-fenxiOri-update";
        }

        return "redirect:/houtai/tiku";
    }

    @RequestMapping("/houtai/updatexuanzefin/{id}")
    public String updatexuanzefin(Model model,
                                  @PathVariable("id") int id,
                                  @RequestParam("tigan") String tigan,
                                  @RequestParam("a") String a,
                                  @RequestParam("b") String b,
                                  @RequestParam("c") String c,
                                  @RequestParam("d") String d,
                                  @RequestParam("ans") String ans
    ) {
        zhangjiehoutaiMapper.updatexuanze(a, b, c, d, tigan, ans, id);
        return "redirect:/houtai/tiku";
    }

    @RequestMapping("/houtai/updatetiankongfin/{id}")
    public String updatetiankongfin(Model model,
                                    @PathVariable("id") int id,
                                    @RequestParam("tigan") String tigan,
                                    @RequestParam("ans") String ans
    ) {
        zhangjiehoutaiMapper.updatetiankong(tigan, ans, id);
        return "redirect:/houtai/tiku";
    }

    @RequestMapping("/houtai/updatepanduanfin/{id}")
    public String updatepanduanfin(Model model,
                                   @PathVariable("id") int id,
                                   @RequestParam("tigan") String tigan,
                                   @RequestParam("a") String a,
                                   @RequestParam("b") String b,
                                   @RequestParam("ans") String ans
    ) {
        zhangjiehoutaiMapper.updatepanduan(a, b, tigan, ans, id);
        return "redirect:/houtai/tiku";
    }

    @RequestMapping("/houtai/updatefenxifin/{id}")
    public String updatefenxifin(Model model,
                                 @PathVariable("id") int id,
                                 @RequestParam("ans") String ans,
                                 @RequestPart("pic") MultipartFile pic
    ) {
        String fileName = pic.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName = date + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        String url = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName;

        try {
            XiniuUtil.upload2Qiniu2(pic.getBytes(), fileName);
            zhangjiehoutaiMapper.updatefenxi(url, ans, id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/houtai/tiku";
    }


    //-----------------------------------------------------

    @RequestMapping("/houtai/add/{id}")
    public String addmain(Model model,
                          @PathVariable("id") int id) {
        int panduan = tiKuMapper.judgeTixin(id);

        //??????
        if (panduan == 1) {
            model.addAttribute("id", id);
            return "houtai-xuanzeOri-add";
        }
        //??????
        if (panduan == 0) {
            model.addAttribute("id", id);
            return "houtai-panduanOri-add";
        }

        //??????
        if (panduan == 2) {
            model.addAttribute("id", id);
            return "houtai-tiankongOri-add";
        }

        //??????
        if (panduan == 3) {
            model.addAttribute("id", id);
            return "houtai-fenxiOri-add";
        }

        return "redirect:/houtai/tiku";
    }

    /**
     * ???????????????
     *
     * @param model
     * @param id
     * @param tigan
     * @param a
     * @param b
     * @param c
     * @param d
     * @param ans
     * @return
     */
    @RequestMapping("/houtai/addxuanzefin/{id}")
    public String addxuanzefin(Model model,
                               @PathVariable("id") int id,
                               @RequestParam("tigan") String tigan,
                               @RequestParam("a") String a,
                               @RequestParam("b") String b,
                               @RequestParam("c") String c,
                               @RequestParam("d") String d,
                               @RequestParam("ans") String ans
    ) {
        try {
            //??????????????????????????????????????????????????????????????????
            tiKuMapper.selectChoicebyTikuid(id).toString();
        } catch (Exception e) {
            zhangjiehoutaiMapper.addxuanze(id, a, b, c, d, tigan, ans);
            System.out.println(id);
        }
        return "redirect:/houtai/tiku";
    }

    /**
     * ???????????????
     *
     * @param model
     * @param id
     * @param tigan
     * @param a
     * @param b
     * @param ans
     * @return
     */
    @RequestMapping("/houtai/addpanduanfin/{id}")
    public String addpanduanfin(Model model,
                                @PathVariable("id") int id,
                                @RequestParam("tigan") String tigan,
                                @RequestParam("a") String a,
                                @RequestParam("b") String b,
                                @RequestParam("ans") String ans
    ) {
        try {
            tiKuMapper.selectBoolbyTikuid(id).toString();
        } catch (Exception e) {
            zhangjiehoutaiMapper.addpanduan(id, a, b, tigan, ans);
        }
        return "redirect:/houtai/tiku";
    }

    /**
     * ???????????????
     *
     * @param model
     * @param id
     * @param pic
     * @param ans
     * @return
     */
    @RequestMapping("/houtai/addfenxifin/{id}")
    public String addfenxifin(Model model,
                              @PathVariable("id") int id,
                              @RequestPart("pic") MultipartFile pic,
                              @RequestParam("ans") String ans
    ) {
        try {
            tiKuMapper.selectFenxibyTikuid(id).toString();
        } catch (Exception e) {
            String fileName = pic.getOriginalFilename();
            String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
            fileName = date + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
            String url = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName;

            try {
                XiniuUtil.upload2Qiniu2(pic.getBytes(), fileName);
                zhangjiehoutaiMapper.addfenxi(id, url, ans);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return "redirect:/houtai/tiku";
    }


    @RequestMapping("/houtai/addtiankongfin/{id}")
    public String addtiankongfin(Model model,
                                 @PathVariable("id") int id,
                                 @RequestParam("tigan") String tigan,
                                 @RequestParam("ans") String ans
    ) {

        try {
            tiKuMapper.selectTiankongbyTikuid(id).toString();
        } catch (Exception e) {
            zhangjiehoutaiMapper.addtiankong(id, tigan, ans);
        }
        return "redirect:/houtai/tiku";
    }

    //-------------------------------------------------------------
    @RequestMapping("/houtai/zhishitiao")
    public String getzhishitiao(Model model) {
        List<ZhishitiaoOriModel> zhishitiao = zhangjiehoutaiMapper.selectAllzhishitiao();
        model.addAttribute("tiku", zhishitiao);
        return "houtai-zhishitiao-main";
    }

    /**
     * ???
     *
     * @param model
     * @param
     * @return
     */
    @GetMapping("/houtai/delzhishitiao/{id}")
    public String delzhishitiao(Model model,
                                @PathVariable("id") int id) {
        zhangjiehoutaiMapper.delzhishitiao(id);
        return "redirect:/houtai/zhishitiao";
    }

    /**
     * ??????
     *
     * @param model
     * @return
     */
    @RequestMapping("/houtai/addzhishitiao")
    public String addzhishitiao(Model model) {
        return "houtai-zhishitiao-add";
    }

    /**
     * ????????????
     *
     * @param model
     * @param miaoshu
     * @param zhangjieid
     * @param parentid
     * @return
     */
    @RequestMapping("/houtai/addzhishitiaofin")
    public String addzhishitiaofin(Model model,
                                   @RequestParam("miaoshu") String miaoshu,
                                   @RequestParam("zhangjieid") int zhangjieid,
                                   @RequestParam("parentid") int parentid
    ) {
        zhangjiehoutaiMapper.addzhishitiao(miaoshu, parentid, zhangjieid);
        return "redirect:/houtai/zhishitiao";
    }


    @RequestMapping("/houtai/updatezhishitiao/{id}")
    public String updatezhishitiao(Model model,
                                   @PathVariable("id") int id) {
        ZhishitiaoOriModel zh = zhangjiehoutaiMapper.selectzhishitiao(id);
        model.addAttribute("zhishi", zh);
        return "houtai-zhishitiao-update";
    }


    @RequestMapping("/houtai/updatezhishitiaofin/{id}")
    public String updatezhishitiaofin(Model model,
                                      @PathVariable("id") int id,
                                      @RequestParam("zhangjieid") int zhangjieid,
                                      @RequestParam("miaoshu") String miaoshu,
                                      @RequestParam("parentid") int parentid
    ) {
        zhangjiehoutaiMapper.updatezhishitiao(miaoshu, parentid, zhangjieid, id);
        return "redirect:/houtai/zhishitiao";
    }

    //---------------------------------------------------------------------------
    @RequestMapping("/houtai/user")
    public String getuser(Model model,
                          HttpSession session) {

        String username = session.getAttribute("username").toString();
        if (!username.equals("root")){
            return "redirect:/houtai/main";
        }
        List<User> user = zhangjiehoutaiMapper.selectAlluser();
        model.addAttribute("user", user);
        return "houtai-user-main";
    }

    /**
     * ???
     *
     * @param model
     * @param
     * @return
     */
    @GetMapping("/houtai/deluser/{id}")
    public String deluser(Model model,
                                @PathVariable("id") int id) {
        zhangjiehoutaiMapper.deluser(id);
        return "redirect:/houtai/user";
    }

    @RequestMapping("/houtai/updateuser/{id}")
    public String updateuser(Model model,
                                   @PathVariable("id") int id) {
        User user = zhangjiehoutaiMapper.selectuser(id);
        if (user.getAuth() == 0){
            zhangjiehoutaiMapper.updateuser(1,id);
        }else {
            zhangjiehoutaiMapper.updateuser(0,id);
        }
        return "redirect:/houtai/user";
    }


}
