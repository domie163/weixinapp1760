<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','serial_number') || $check_field('add','serial_number') || $check_field('set','serial_number')" label="物品编号" name="serial_number">
                            <uni-easyinput type="text" v-model="form['serial_number']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','serial_number')) || (!form['purchase_order_id'] && $check_field('add','serial_number'))" :disabled="disabledObj['serial_number_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','serial_number')">
                  {{ form['serial_number'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','item_name') || $check_field('add','item_name') || $check_field('set','item_name')" label="物品名称" name="item_name">
                            <uni-easyinput type="text" v-model="form['item_name']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','item_name')) || (!form['purchase_order_id'] && $check_field('add','item_name'))" :disabled="disabledObj['item_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','item_name')">
                  {{ form['item_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','unit_price') || $check_field('add','unit_price') || $check_field('set','unit_price')" label="单件价格" name="unit_price">
                            <uni-easyinput type="text" v-model="form['unit_price']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','unit_price')) || (!form['purchase_order_id'] && $check_field('add','unit_price'))" :disabled="disabledObj['unit_price_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','unit_price')">
                  {{ form['unit_price'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','selling_users') || $check_field('add','selling_users') || $check_field('set','selling_users')" label="售卖用户" name="selling_users">
                    <uni-data-select
                  id="form_selling_users"
                  v-model="form['selling_users']"
                  :localdata="list_user_selling_users"
                  :clear="!disabledObj['selling_users_isDisabled']"
                  :disabled="disabledObj['selling_users_isDisabled']"
                  v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','selling_users')) || (!form['purchase_order_id'] && $check_field('add','selling_users'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['selling_users']"
                  :localdata="list_user_selling_users"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','selling_users')" id="selling_users"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','purchase_user') || $check_field('add','purchase_user') || $check_field('set','purchase_user')" label="购买用户" name="purchase_user">
                    <uni-data-select
                  id="form_purchase_user"
                  v-model="form['purchase_user']"
                  :localdata="list_user_purchase_user"
                  :clear="!disabledObj['purchase_user_isDisabled']"
                  :disabled="disabledObj['purchase_user_isDisabled']"
                  v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','purchase_user')) || (!form['purchase_order_id'] && $check_field('add','purchase_user'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['purchase_user']"
                  :localdata="list_user_purchase_user"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','purchase_user')" id="purchase_user"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" label="用户姓名" name="user_name">
                            <uni-easyinput type="text" v-model="form['user_name']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','user_name')) || (!form['purchase_order_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','user_name')">
                  {{ form['user_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','purchase_quantity') || $check_field('add','purchase_quantity') || $check_field('set','purchase_quantity')" label="购买数量" name="purchase_quantity">
                            <uni-easyinput type="text" v-model="form['purchase_quantity']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','purchase_quantity')) || (!form['purchase_order_id'] && $check_field('add','purchase_quantity'))" :disabled="disabledObj['purchase_quantity_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','purchase_quantity')">
                  {{ form['purchase_quantity'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','total_price') || $check_field('add','total_price') || $check_field('set','total_price')" label="总计价格" name="total_price">
                            <uni-easyinput type="text" v-model="form['total_price']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','total_price')) || (!form['purchase_order_id'] && $check_field('add','total_price'))"  @focus="set_total_price()" :disabled="disabledObj['total_price_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','total_price')">
                  {{ form['total_price'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','contact_information') || $check_field('add','contact_information') || $check_field('set','contact_information')" label="联系方式" name="contact_information">
                            <uni-easyinput type="text" v-model="form['contact_information']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','contact_information')) || (!form['purchase_order_id'] && $check_field('add','contact_information'))" :disabled="disabledObj['contact_information_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','contact_information')">
                  {{ form['contact_information'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','shipping_address') || $check_field('add','shipping_address') || $check_field('set','shipping_address')" label="收货地址" name="shipping_address">
                            <uni-easyinput type="text" v-model="form['shipping_address']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','shipping_address')) || (!form['purchase_order_id'] && $check_field('add','shipping_address'))" :disabled="disabledObj['shipping_address_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','shipping_address')">
                  {{ form['shipping_address'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','purchase_notes') || $check_field('add','purchase_notes') || $check_field('set','purchase_notes')" label="购买备注" name="purchase_notes">
                    <uni-easyinput type="textarea" v-model="form['purchase_notes']" v-if="user_group === '管理员' || (form['purchase_order_id'] && $check_field('set','purchase_notes')) || (!form['purchase_order_id'] && $check_field('add','purchase_notes'))" :disabled="disabledObj['purchase_notes_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','purchase_notes')">
                  {{ form['purchase_notes'] }}
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
      field: "purchase_order_id",
      url_add: "~/api/purchase_order/add?",
      url_set: "~/api/purchase_order/set?",
      url_get_obj: "~/api/purchase_order/get_obj?",
      url_upload: "~/api/purchase_order/upload?",

      query: {
        "purchase_order_id": 0,
      },

      form: {
            "serial_number":  '', // 物品编号
                    "item_name":  '', // 物品名称
                    "unit_price":  '', // 单件价格
                    "selling_users": 0, // 售卖用户
                    "purchase_user": 0, // 购买用户
                    "user_name":  '', // 用户姓名
                    "purchase_quantity":  0 , // 购买数量
                    "total_price":  '', // 总计价格
                    "contact_information":  '', // 联系方式
                    "shipping_address":  '', // 收货地址
                    "purchase_notes":  '', // 购买备注
                                    "purchase_order_id": 0, // ID
                
              },
          disabledObj:{
                        "serial_number_isDisabled": false,
                                "item_name_isDisabled": false,
                                "unit_price_isDisabled": false,
                                "selling_users_isDisabled": false,
                                "purchase_user_isDisabled": false,
                                "user_name_isDisabled": false,
                                                    "total_price_isDisabled": false,
                                "contact_information_isDisabled": false,
                                "shipping_address_isDisabled": false,
                                "purchase_notes_isDisabled": false,
                                  },
                                                                                            // 用户列表
            list_user_selling_users: [],
                                                    // 用户列表
            list_user_purchase_user: [],
                        // 用户组
            group_user_purchase_user: "",
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
            url: _self.$fullUrl('/api/purchase_order/upload?'),
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
            url: _self.$fullUrl('/api/purchase_order/upload?'),
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
    async get_list_user_selling_users() {
      // if(this.user_group !== "管理员" && this.form["selling_users"] === 0) {
      //     this.form["selling_users"] = this.user.user_id;
      // }
      var json = await this.$get("~/api/user/get_list?user_group=注册用户");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_user_selling_users.push({value:o.user_id,text:o.nickname + '-' + o.username}));
      }
      else if(json.error){
        console.error(json.error);
      }
    },
            
                /**
     * 获取注册用户用户列表
     */
    async get_list_user_purchase_user() {
      // if(this.user_group !== "管理员" && this.form["purchase_user"] === 0) {
      //     this.form["purchase_user"] = this.user.user_id;
      // }
      var json = await this.$get("~/api/user/get_list?user_group=注册用户");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_user_purchase_user.push({value:o.user_id,text:o.nickname + '-' + o.username}));
      }
      else if(json.error){
        console.error(json.error);
      }
    },
            /**
     * 获取注册用户用户组
     */
    async get_group_user_purchase_user() {
      this.form["purchase_user"] = this.user.user_id;
      var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
      if(json.result && json.result.obj){
        this.group_user_purchase_user = json.result.obj;
        this.get_user_session_purchase_user(this.form['purchase_user'])
      }
      else if(json.error){
        console.error(json.error);
      }
    },
    get_user_session_purchase_user(id){
      var _this = this;
      var user_id = {"user_id":id}
      var url = "~/api/"+_this.group_user_purchase_user.source_table+"/get_obj?"
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
                  if (arr[i] !== "purchase_user") {
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
            
            
            
            
                            set_total_price(){
      this.form.total_price = parseFloat(this.form.unit_price) * parseFloat(this.form.purchase_quantity)
      let r = /^\+?[1-9][0-9]*$/; // 正整数
      if(!r.test(this.form.total_price) ){
        this.form.total_price = this.form.total_price.toFixed(2);
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
        bl = this.$check_action('/purchase_order/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/purchase_order/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/purchase_order/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/purchase_order/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/purchase_order/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
                                    this.get_list_user_selling_users();
                        this.get_list_user_purchase_user();
            this.get_group_user_purchase_user();
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
