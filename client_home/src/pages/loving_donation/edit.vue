<template>
	<view class="diy_edit page_loving_donation" id="loving_donation_edit">
		<view class='warp'>
			<view class='container'>
				<view class='row'>
						<view v-if="$check_field('set','donors') || $check_field('add','donors') || $check_field('get','donors')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								捐助人员:
							</text>
						</view>
						<view class="diy_field diy_down diy_text_row diy_select_flex">
							<uni-data-select
									id="form_donors"
									v-model="form['donors']"
									:localdata="list_user_donors"
									:clear="!disabledObj['donors_isDisabled']"
									:disabled="disabledObj['donors_isDisabled']"
									v-if="(form['donors'] && $check_field('set','donors')) || (!form['donors'] && $check_field('add','donors'))"
							></uni-data-select>
							<text v-else-if="$check_field('get','donors')">{{ form['donors'] }}</text>
						</view>
					</view>
							<view v-if="$check_field('set','date_of_donation') || $check_field('add','date_of_donation') || $check_field('get','date_of_donation')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								捐助日期:
							</text>
						</view>
								<!-- 日期 -->
						<view class="diy_field diy_date">
							<uni-datetime-picker :disabled="disabledObj['date_of_donation_isDisabled']" type="date" id="form_date_of_donation" v-model="form['date_of_donation']" placeholder="请输入捐助日期" v-if="(form['date_of_donation'] && $check_field('set','date_of_donation')) || (!form['date_of_donation'] && $check_field('add','date_of_donation'))" />
							<text v-else-if="$check_field('get','date_of_donation')">{{ form['date_of_donation'] }}</text>
						</view>
							</view>
							<view v-if="$check_field('set','donation_items') || $check_field('add','donation_items') || $check_field('get','donation_items')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								捐助物品:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_donation_items" v-model="form['donation_items']" placeholder="请输入捐助物品" v-if="(form['donation_items'] && $check_field('set','donation_items')) || (!form['donation_items'] && $check_field('add','donation_items'))" :disabled="disabledObj['donation_items_isDisabled']" />
							<text v-else-if="$check_field('get','donation_items')">{{ form['donation_items'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','number_of_donations') || $check_field('add','number_of_donations') || $check_field('get','number_of_donations')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								捐助数量:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_number_of_donations" v-model="form['number_of_donations']" placeholder="请输入捐助数量" v-if="(form['number_of_donations'] && $check_field('set','number_of_donations')) || (!form['number_of_donations'] && $check_field('add','number_of_donations'))" :disabled="disabledObj['number_of_donations_isDisabled']" />
							<text v-else-if="$check_field('get','number_of_donations')">{{ form['number_of_donations'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','remarks') || $check_field('add','remarks') || $check_field('get','remarks')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								备注信息:
							</text>
						</view>
								<!-- 多文本 -->
						<view class="diy_field diy_desc diy_text_row">
							<textarea id="form_remarks" v-model="form['remarks']" v-if="(form['remarks'] && $check_field('set','remarks')) || (!form['remarks'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"/>
							<text v-else-if="$check_field('get','remarks')">{{ form['remarks'] }}</text>
						</view>
							</view>
	
				</view>
				<view class="row">
					<view class="col-12">
						<view class="btn_box">
							<button class="btn_submit primary_btn" @click="submit()">提交</button>
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
	mixins:[mixin],
	components:{},
	data(){
		return{
			url_get_obj:"~/api/loving_donation/get_obj?",
			url_add:"~/api/loving_donation/add?",
			url_set:"~/api/loving_donation/set?",

			// 登录权限
			oauth: {
				"signIn": true,
				"user_group": []
			},

			// 查询条件
			query: {
					"donors": 0,
						"date_of_donation": "",
						"donation_items": "",
						"number_of_donations": 0,
						"remarks": "",
					"loving_donation_id": 0
			},

			obj: {
					"donors": 0, // 捐助人员
						"date_of_donation": this.$toTime(new Date().getTime(), "yyyy-MM-dd"),
						"donation_items":  '', // 捐助物品
						"number_of_donations":  0 , // 捐助数量
						"remarks":  '', // 备注信息
					"loving_donation_id": 0,

			},

			// 表单字段
			form: {
					"donors": 0, // 捐助人员
						"date_of_donation": this.$toTime(new Date().getTime(), "yyyy-MM-dd"),
						"donation_items":  '', // 捐助物品
						"number_of_donations":  0 , // 捐助数量
						"remarks":  '', // 备注信息
					"loving_donation_id": 0,
			},
			disabledObj:{
					"donors_isDisabled": false,
						"date_of_donation_isDisabled": false,
						"donation_items_isDisabled": false,
								"remarks_isDisabled": false,
				},

					// 用户列表
			list_user_donors: [],
									
			field:"loving_donation_id",
			table_key:"loving_donation",

		}
	},
	methods: {
				/**
		 * 获取注册用户用户列表
		 */
		async get_list_user_donors() {
			// if(this.user_group !== "管理员" && this.form["donors"] === 0) {
			//     this.form["donors"] = this.user.user_id;
			// }
			var json = await this.$get("~/api/user/get_list?user_group=注册用户");
			if(json.result && json.result.list){
				json.result.list.map((o) => this.list_user_donors.push({value:o.user_id,text:o.nickname + '-' + o.username}));
			}
			else if(json.error){
				console.error(json.error);
			}
		},
				async get_user_session_donors(){
			var _this = this;
			var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
			if(json.result && json.result.obj){
				var source_table = json.result.obj.source_table;
				var user_id = _this.$store.state.user.user_id;
				if (user_id){
					var url = "~/api/"+source_table+"/get_obj?"
					this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
						if (res.result && res.result.obj) {
							var arr = []
							for (let key in res.result.obj) {
								arr.push(key)
							}
							var arrForm = []
							for (let key in _this.form) {
								arrForm.push(key)
							}
							_this.form["donors"] = user_id
							_this.disabledObj['donors' + '_isDisabled'] = true
							for (var i=0;i<arr.length;i++){
                if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                  for (var j = 0; j < arrForm.length; j++) {
                    if (arr[i] === arrForm[j]) {
                      if (arr[i] !== "donors") {
                        _this.form[arrForm[j]] = res.result.obj[arr[i]]
                        _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                        break;
                      }
                    }
                  }
                }
							}
						}
					});
				}
			}
			else if(json.error){
				console.error(json.error);
			}
		},
	
	
				
	
				
	
				
	
				
	
			change_file(key_name){
			var _self = this;
				this.$chooseFile().then(res=>{
					console.log(res)

						const uploadTask = uni.uploadFile({
							url: _self.$fullUrl("/api/feedback/upload?"),
							filePath: res[0].path,
							name: "file",
							formData: {
								i_want_to_customize: "test",
							},
							header: {
								"x-auth-token": _self.$store.state.user.token,
							},
							success: function(uploadFileRes) {
								console.log(uploadFileRes)
								var filename = JSON.parse(uploadFileRes.data).result.url;
								_self.form[key_name] = filename;
							},
						});

						uploadTask.onProgressUpdate(function(res) {
							_self.percent = res.progress;
							console.log("上传进度" + res.progress);
							console.log("已经上传的数据长度" + res.totalBytesSent);
							console.log(
								"预期需要上传的数据总长度" + res.totalBytesExpectedToSend
							);
						});

				})
		},
		change_img(key_name) {
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
						url: _self.$fullUrl('/api/loving_donation/upload?'),
						filePath: tempFilePaths[0],
						name: 'file',
						formData: {
							'loving_donation': 'test'
						},
						header: {
							'x-auth-token': _self.$store.state.user.token
						},
						success: function(uploadFileRes) {
							var filename = JSON.parse(uploadFileRes.data).result.url
							var img_url = filename
							_self.form[key_name] = img_url
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
							if (this.form["date_of_donation"] && JSON.stringify(this.form["date_of_donation"]).indexOf("-")===-1) {
				this.form["date_of_donation"] = this.$toTime(parseInt(this.form["date_of_donation"]), "yyyy-MM-dd")
			}
										uni.db.del("form");
			return param;
		},

		/**
		 * 获取对象后获取缓存表单
		 * @param {Object} json
		 * @param {Object} func
		 */
		get_obj_after(json ,func){
			var form = uni.db.get("form");
			var obj = Object.assign({} ,form ,this.obj);
			if (form) {
				this.obj = uni.push(this.obj ,obj);
			}
			if (form) {
				this.form = uni.push(this.form ,form);
			}
			if(func){
				func(json);
			}
		},

	},
	onLoad(){
				this.get_user_session_donors();
				this.get_list_user_donors();
														},
}
</script>

<style scoped>
	input{
		font-size: 10px;
	}

	.form_edit {
		background-color: #fff;
		margin-bottom: 0.5rem;
		padding: 1rem;
		font-size: 10px;
	}

	.item {
		display: flex;
		padding: 0.2rem 0;
	}

	.left_text {
		flex: 0 0 25%;
		display: flex;
		align-items: center;
	}

	.right_text {
		flex: 0 0 75%;
		border-bottom: 1px solid #eee;
	}
	.right_text.btn_warp{
		border-bottom: 0;
	}

	.btn_submit {
		text-align: center;
		background-color: #fff;
		padding: 0.3rem;
		margin: 0.1rem 1rem;
		border: 1px solid #eee;
		border-radius: 0.5rem;
	}

	.btn_submit:hover {
		opacity: 0.5;
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




</style>
<style scoped>
/*新样式*/
.diy_text_row {
  display: inline-block;
}
.container {
	margin-top:1rem ;
	padding: 1rem;
    -webkit-box-shadow: 0px 0px 0px #888888;
    box-shadow: 0px 0px 0px #888888;
}
.primary_btn{
		background-color: #22B8B8;
		color: #FFFFFF;
	}
	.btn_submit{
		padding: 0;
		margin-top:1rem ;
	}
	.row-item {
		padding: 10px 10px;
	    background: #f8f6fc;
		margin-bottom: 1rem;
	}
	.diy_field{
		padding-left: 1rem;
	}
	.diy_title{
		align-items: center;
        font-size: 14px;
        color: #333;
	}

	.row-item{
		display: flex !important;
		align-items: baseline;
	}
	.diy_select_flex{
		flex: 1;
	}
	.query_select{
		flex: 1;
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
</style>

