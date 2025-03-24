<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','publisher') || $check_field('add','publisher') || $check_field('set','publisher')" label="发布人员" name="publisher">
                    <uni-data-select
                  id="form_publisher"
                  v-model="form['publisher']"
                  :localdata="list_user_publisher"
                  :clear="!disabledObj['publisher_isDisabled']"
                  :disabled="disabledObj['publisher_isDisabled']"
                  v-if="user_group === '管理员' || (form['barter_id'] && $check_field('set','publisher')) || (!form['barter_id'] && $check_field('add','publisher'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['publisher']"
                  :localdata="list_user_publisher"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','publisher')" id="publisher"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_name') || $check_field('add','item_name') || $check_field('set','item_name')" label="物品名称" name="item_name">
                            <uni-easyinput type="text" v-model="form['item_name']" v-if="user_group === '管理员' || (form['barter_id'] && $check_field('set','item_name')) || (!form['barter_id'] && $check_field('add','item_name'))" :disabled="disabledObj['item_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_name')">
                  {{ form['item_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_type') || $check_field('add','item_type') || $check_field('set','item_type')" label="物品类型" name="item_type">
                    <uni-data-select
                  v-model="form.item_type"
                  :localdata="list_item_type"
                  :clear="!disabledObj['item_type_isDisabled']"
                  :disabled="disabledObj['item_type_isDisabled']"
                  v-if="user_group === '管理员' || (form['barter_id'] && $check_field('set','item_type')) || (!form['barter_id'] && $check_field('add','item_type'))"
                ></uni-data-select>
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_type')">
                  {{ form['item_type'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','required_barter') || $check_field('add','required_barter') || $check_field('set','required_barter')" label="所需易物" name="required_barter">
                            <uni-easyinput type="text" v-model="form['required_barter']" v-if="user_group === '管理员' || (form['barter_id'] && $check_field('set','required_barter')) || (!form['barter_id'] && $check_field('add','required_barter'))" :disabled="disabledObj['required_barter_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','required_barter')">
                  {{ form['required_barter'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_picture') || $check_field('add','item_picture') || $check_field('set','item_picture')" label="物品图片" name="item_picture">
                    <!-- 修改权限 -->
                <view class="diy_field diy_img" v-if="form['item_picture'] && $check_field('set','item_picture')">
                  <image v-if="disabledObj['item_picture_isDisabled']" :src="$fullUrl(form['item_picture'])" />
                  <image v-if="!disabledObj['item_picture_isDisabled']" :src="$fullUrl(form['item_picture'])" @click="change_img('item_picture')" />
                </view>
                <!-- 添加权限 -->
                <view class="diy_field diy_img" v-else-if="!form['item_picture'] && $check_field('add','item_picture')">
                  <view v-if="disabledObj['item_picture_isDisabled']" class="btn_add_img">
                    <text>+</text>
                  </view>
                  <view v-if="!disabledObj['item_picture_isDisabled']" class="btn_add_img" @click="change_img('item_picture')">
                    <text>+</text>
                  </view>
                </view>
                <!-- 查询权限 -->
                <view class="diy_field diy_img" v-else-if="$check_field('get','item_picture')">
                  <image :src="$fullUrl(form['item_picture'])" />
                </view>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','contact_information') || $check_field('add','contact_information') || $check_field('set','contact_information')" label="联系方式" name="contact_information">
                            <uni-easyinput type="text" v-model="form['contact_information']" v-if="user_group === '管理员' || (form['barter_id'] && $check_field('set','contact_information')) || (!form['barter_id'] && $check_field('add','contact_information'))" :disabled="disabledObj['contact_information_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','contact_information')">
                  {{ form['contact_information'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_introduction') || $check_field('add','item_introduction') || $check_field('set','item_introduction')" label="物品介绍" name="item_introduction">
                    <uni-easyinput type="textarea" v-model="form['item_introduction']" v-if="user_group === '管理员' || (form['barter_id'] && $check_field('set','item_introduction')) || (!form['barter_id'] && $check_field('add','item_introduction'))" :disabled="disabledObj['item_introduction_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_introduction')">
                  {{ form['item_introduction'] }}
                </text>
                  </uni-forms-item>


            </uni-forms>
            <view class="form_button">
              <button size="mini" type="primary" @click="submit()" class="primary_btn">提交</button>
              <button size="mini" @click="cancel()">取消</button>
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import mixin from "@/libs/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      field: "barter_id",
      url_add: "~/api/barter/add?",
      url_set: "~/api/barter/set?",
      url_get_obj: "~/api/barter/get_obj?",
      url_upload: "~/api/barter/upload?",

      query: {
        "barter_id": 0,
      },

      form: {
            "publisher": 0, // 发布人员
                    "item_name":  '', // 物品名称
                    "item_type":  '', // 物品类型
                    "required_barter":  '', // 所需易物
                    "item_picture":  '', // 物品图片
                    "contact_information":  '', // 联系方式
                    "item_introduction":  '', // 物品介绍
                                    "barter_id": 0, // ID
                
              },
          disabledObj:{
                        "publisher_isDisabled": false,
                                "item_name_isDisabled": false,
                                "item_type_isDisabled": false,
                                "required_barter_isDisabled": false,
                                "item_picture_isDisabled": false,
                                "contact_information_isDisabled": false,
                                "item_introduction_isDisabled": false,
                                  },
                                // 用户列表
            list_user_publisher: [],
                        // 用户组
            group_user_publisher: "",
                                                              // 物品类型选项列表
          list_item_type: [],
                                                                                                                      }
  },
  methods: {
    changeLog(v,value){
      this.form[value] = v
    },
    /**
     * 上传文件
     * @param {Object} param文件参数
     */
    change_file(key_name){
      var _self = this;
      // 选择图像方法
      uni.chooseFile({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/barter/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 上传图片
     * @param {Object} param文件参数
     */
    change_img(key_name){
      var _self = this;
      _self.upload_img_flag = false
      // 选择图像方法
      uni.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/barter/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 获取对象后获取缓存表单
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_before(param){
      var form = uni.db.get("form");
      if (form) {
        delete(form.examine_state)
        delete(form.examine_reply)
        this.obj = uni.push(this.obj ,form);
        this.form = uni.push(this.form ,form);
      }
      var arr = []
      for (let key in form) {
        arr.push(key)
      }
      for (var i=0;i<arr.length;i++){
        this.disabledObj[arr[i] + '_isDisabled'] = true
      }
                                                              uni.db.del("form");
      return param;
    },
            /**
     * 获取注册用户用户列表
     */
    async get_list_user_publisher() {
      // if(this.user_group !== "管理员" && this.form["publisher"] === 0) {
      //     this.form["publisher"] = this.user.user_id;
      // }
      var json = await this.$get("~/api/user/get_list?user_group=注册用户");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_user_publisher.push({value:o.user_id,text:o.nickname + '-' + o.username}));
      }
      else if(json.error){
        console.error(json.error);
      }
    },
            /**
     * 获取注册用户用户组
     */
    async get_group_user_publisher() {
      this.form["publisher"] = this.user.user_id;
      var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
      if(json.result && json.result.obj){
        this.group_user_publisher = json.result.obj;
        this.get_user_session_publisher(this.form['publisher'])
      }
      else if(json.error){
        console.error(json.error);
      }
    },
    get_user_session_publisher(id){
      var _this = this;
      var user_id = {"user_id":id}
      var url = "~/api/"+_this.group_user_publisher.source_table+"/get_obj?"
      this.$get(url, user_id, function(res) {
        if (res.result && res.result.obj) {
          var arr = []
          for (let key in res.result.obj) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in _this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arr[i] === arrForm[j]) {
                  if (arr[i] !== "publisher") {
                    _this.form[arrForm[j]] = res.result.obj[arr[i]]
                    _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                    break;
                  } else {
                    _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  }
                }
              }
            }
          }
        }
      });
    },
            
            
            /**
     * 获取物品类型列表
     */
    async get_list_item_type() {
              var json = await this.$get("~/api/item_classification/get_list?");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_item_type.push({value:o.item_type,text:o.item_type}));
      }
      else if(json.error){
        console.error(json.error);
      }
        },
        
            
            
            
            
    
    /**
     * 获取对象之后
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_after(json, func){
                                                            },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/barter/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/barter/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/barter/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/barter/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/barter/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
            this.get_list_user_publisher();
            this.get_group_user_publisher();
                            this.get_list_item_type();
                                          },
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.form_button{
  padding-bottom: 15px;
  display: flex;
}
.form_button button{
  width: 40%;
}
.query_select{
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline:none;
}

.query_option{
  width: 100%;
}

.btn_add_img{
  color: #D3D3D3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text{
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.form_button {
  padding-bottom: 15px;
  display: flex;
}
.form_button button {
  width: 40%;
}
.query_select {
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline: none;
}

.query_option {
  width: 100%;
}

.btn_add_img {
  color: #d3d3d3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text {
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
/*新样式*/
.uni-forms{
padding-top:1rem;
}
.uni-forms-item {
	padding: 6px 10px;
    background: #f8f6fc;
}
.uni-forms .is-input-border{
	border: 0;
}
.container{
	    -webkit-box-shadow: 0px 0px 0px #888888;
	    box-shadow: 0px 0px 0px #888888;
}
.form_button .primary_btn{
		background-color: #22B8B8;
		color: #FFFFFF;
	}
</style>
