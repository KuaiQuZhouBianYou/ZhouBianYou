package com.phone1000.wanttozhoubianyou.model.around;

import java.util.List;

/**
 * Created by 落叶 on 2016-11-28.
 */
public class aroundHost {


    /**
     * code : 1000
     * message : 请求成功
     * content : {"hotScenic":[{"scenicId":"1305","scenicName":"上海迪士尼","mudiPic":"http://cdn6.yaochufa.com/group1/M00/00/D6/ooYBAFcE2jaAcMppAABlCUwtSj0893.jpg"},{"scenicId":"16","scenicName":"九寨沟","mudiPic":"http://cdn6.jinxidao.com/uploads/201510/5624c99a9722c.jpg"},{"scenicId":"1003","scenicName":"珠海长隆","mudiPic":"http://cdn6.jinxidao.com/group1/M00/02/7A/ooYBAFdrTfqASQkkAABB5CTyNw0551.jpg"},{"scenicId":"1094","scenicName":"江南古镇","mudiPic":"http://cdn6.jinxidao.com/group1/M00/01/B6/oYYBAFc6gp6AHHWJAABljQK_adM721.jpg"},{"scenicId":"1213","scenicName":"香港迪士尼","mudiPic":"http://cdn6.yaochufa.com/group1/M00/00/E2/oYYBAFcGOziAZ5aeAABWI22WlW8807.jpg"},{"scenicId":"17","scenicName":"鼓浪屿","mudiPic":"http://cdn6.jinxidao.com/uploads/201510/5625e11230373.jpg"}],"hotCity":[{"areaCode":"350200","cityName":"厦门","pinYin":"XiaMen","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c16c33331.jpg","showPic":1},{"areaCode":"310100","cityName":"上海","pinYin":"ShangHai","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c163560ba.jpg","showPic":1},{"areaCode":"320500","cityName":"苏州","pinYin":"SuZhou","pic":"http://cdn6.jinxidao.com/group1/M00/01/B6/ooYBAFc6hCaAX3mXAABkNqOYsrU494.jpg","showPic":1},{"areaCode":"330100","cityName":"杭州","pinYin":"HangZhou","pic":"http://cdn6.jinxidao.com/uploads/201601/568e102676687.jpg","showPic":1},{"areaCode":"440100","cityName":"广州","pinYin":"GuangZhou","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c159b2a27.jpg","showPic":1},{"areaCode":"820100","cityName":"澳门","pinYin":"Macau","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c1b6ceb45.jpg","showPic":1},{"areaCode":"810100","cityName":"香港","pinYin":"Hong Kong","pic":"http://cdn6.yaochufa.com/group1/M00/00/E2/oYYBAFcGOC2ALysWAABaoYFj57I283.jpg","showPic":1},{"areaCode":"530700","cityName":"丽江","pinYin":"LiJiang","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c198a1c90.jpg","showPic":1},{"areaCode":"110100","cityName":"北京","pinYin":"BeiJing","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c167d80ba.jpg","showPic":1},{"areaCode":"460200","cityName":"三亚","pinYin":"SanYa","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c17331ce0.jpg","showPic":1},{"areaCode":"510100","cityName":"成都","pinYin":"ChengDu","pic":"http://cdn6.jinxidao.com/uploads/201511/56552e79484e1.jpg","showPic":1},{"areaCode":"710200","cityName":"台北","pinYin":"Taipei","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c1bbd1454.jpg","showPic":1},{"areaCode":"430800","cityName":"张家界","pinYin":"ZhangJiaJie","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c1aa398c7.jpg","showPic":1},{"areaCode":"450300","cityName":"桂林","pinYin":"GuiLin","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c178cead2.jpg","showPic":1},{"areaCode":"610100","cityName":"西安","pinYin":"XiAn","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c1a358aeb.jpg","showPic":1},{"areaCode":"370200","cityName":"青岛","pinYin":"QingDao","pic":"http://cdn6.jinxidao.com/uploads/201510/5624c1adca1bf.jpg","showPic":1}]}
     */
    private int code;
    private String message;
    private ContentBean content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        private List<HotScenicBean> hotScenic;
        private List<HotCityBean> hotCity;

        public List<HotScenicBean> getHotScenic() {
            return hotScenic;
        }

        public void setHotScenic(List<HotScenicBean> hotScenic) {
            this.hotScenic = hotScenic;
        }

        public List<HotCityBean> getHotCity() {
            return hotCity;
        }

        public void setHotCity(List<HotCityBean> hotCity) {
            this.hotCity = hotCity;
        }

        public static class HotScenicBean {
            /**
             * scenicId : 1305
             * scenicName : 上海迪士尼
             * mudiPic : http://cdn6.yaochufa.com/group1/M00/00/D6/ooYBAFcE2jaAcMppAABlCUwtSj0893.jpg
             */

            private String scenicId;
            private String scenicName;
            private String mudiPic;

            public String getScenicId() {
                return scenicId;
            }

            public void setScenicId(String scenicId) {
                this.scenicId = scenicId;
            }

            public String getScenicName() {
                return scenicName;
            }

            public void setScenicName(String scenicName) {
                this.scenicName = scenicName;
            }

            public String getMudiPic() {
                return mudiPic;
            }

            public void setMudiPic(String mudiPic) {
                this.mudiPic = mudiPic;
            }
        }

        public static class HotCityBean {
            /**
             * areaCode : 350200
             * cityName : 厦门
             * pinYin : XiaMen
             * pic : http://cdn6.jinxidao.com/uploads/201510/5624c16c33331.jpg
             * showPic : 1
             */

            private String areaCode;
            private String cityName;
            private String pinYin;
            private String pic;
            private int showPic;

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getPinYin() {
                return pinYin;
            }

            public void setPinYin(String pinYin) {
                this.pinYin = pinYin;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getShowPic() {
                return showPic;
            }

            public void setShowPic(int showPic) {
                this.showPic = showPic;
            }
        }
    }
}
