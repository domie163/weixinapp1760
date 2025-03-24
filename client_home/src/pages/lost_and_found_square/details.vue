<template>
	<view class="page_lost_and_found_square diy_detail diy_detail--new" id="lost_and_found_square_details">
		<view class="warp">
			<view class="container-fluid">
				<view class="row">
					<view v-if="$check_field('get','publisher')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>发布人员</span>
							</view>
								<view class="diy_field diy_uid">
								{{ get_user_publisher(obj['publisher']) }}
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','item_name')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>物品名称</span>
							</view>
								<view class="diy_field diy_text">
								<span>
									{{ obj["item_name"] }}
								</span>
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','item_type')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>物品类型</span>
							</view>
								<view class="diy_field diy_text">
								<span>
									{{ obj["item_type"] }}
								</span>
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','item_picture')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>物品图片</span>
							</view>
								<view class="diy_field diy_img">
								<img :src="$fullUrl(obj['item_picture'])" />
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','lost_time')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>丢失时间</span>
							</view>
								<view class="diy_field diy_datetime">
								<span>
									{{ $toTime(obj["lost_time"],"yyyy-MM-dd hh:mm:ss") }}
								</span>
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','lost_location')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>丢失地点</span>
							</view>
								<view class="diy_field diy_text">
								<span>
									{{ obj["lost_location"] }}
								</span>
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','contact_information')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>联系方式</span>
							</view>
								<view class="diy_field diy_phone">
								<span>
									{{ obj['contact_information'] }}
								</span>
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','open_the_reward_offer')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>开启悬赏</span>
							</view>
								<view class="diy_field diy_text">
								<span>
									{{ obj["open_the_reward_offer"] }}
								</span>
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','reward_price')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>悬赏价格</span>
							</view>
								<view class="diy_field diy_text">
								<span>
									{{ obj["reward_price"] }}
								</span>
							</view>
							</view>
					</view>
					<view v-if="$check_field('get','item_description')" class="col-12 col-6">
						<view class="view">
							<view class="diy_title">
								<span>物品描述</span>
							</view>
								<view class="diy_field diy_desc">
								<span>
									{{ obj["item_description"] }}
								</span>
							</view>
							</view>
					</view>
				</view>
			</view>
		</view>


		<!-- 地图 -->
		<div class="warp">
			<div class="container-fluid" style="margin:10px 0;">
				<div class="row">
		 			<div class="col">
						<view class="col-12 col-6">
							<view class="view">
								<view class="diy_title">
									<span>当前定位</span>
								</view>
								<view class="diy_field diy_datetime">
								<span>
									{{ obj["location_address"] }}
								</span>
								</view>
							</view>
						</view>
						<map style="width: 100%; height: 300px;" :latitude="obj.location_lat" :longitude="obj.location_lng" :markers="[{latitude: parseFloat(obj.location_lat),longitude: parseFloat(obj.location_lng),iconPath: '../../static/map_marker.png'}]">
						</map>
					</div>
				</div>
			</div>
		</div>

		<!-- 评论区标题 -->
		<view class="bar_title comment_title">
			<view class="title">
				<navigator :url="bar_url">
					<text>
						{{ bar_title }}
					</text>
				</navigator>
			</view>
		</view>

		<!-- 评论区列表 -->
		<list_comment :list="list_comment"></list_comment>

		<view class="warp">
			<view class="container-fluid container-fluid-comment--new">
				<view class="row">
					<view class="col">

<!--						<button type="button" class="btn btn-primary" @click="cancel()">返回</button>-->



						<navigator class="btn link"
							:url="'/pages/comment/edit?source_table=lost_and_found_square&source_field=lost_and_found_square_id&source_id=' + obj['lost_and_found_square_id']">
							评论
						</navigator>


					</view>
				</view>
			</view>
		</view>


	</view>
</template>

<script>
	import list_comment from "@/components/diy/list_comment.vue";
	import bar_title from "@/components/diy/bar_title.vue";
	import form_editor from "@/components/diy/form_editor.vue";

	import mixin from "@/libs/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {
			list_comment,
			bar_title,
			form_editor
		},
		data() {
			return {
				url_get_obj: "~/api/lost_and_found_square/get_obj?",
				field: "lost_and_found_square_id",
				query: {
					"lost_and_found_square_id": 0
				},
				// 商品详情初始化
				obj: {
						"publisher": 0,
							"item_name": "",
							"item_type": "",
							"item_picture": "",
							"lost_time": "",
							"lost_location": "",
							"contact_information": "",
							"open_the_reward_offer": "",
							"reward_price": "",
							"item_description": "",
						"lost_and_found_square_id": 0,
					"location_address": "", // 定位地址
					"location_lng": "", // 定位地址经度
					"location_lat": "", // 定位地址纬度

				},
				// 点赞
				praise: 0,
				// 点赞状态
				state_praise: false,
				// 收藏
				collect: 0,
				// 收藏状态
				state_collect: false,
				// 评论列表初始化
				list_comment: [],
				// 评论bar标题
				bar_title: "评论区",
				// 评论bar的链接
				bar_url: "",
					// 用户列表
				list_user_publisher: [],
													}
		},
		methods: {
			openUrl(url) {
				console.log(1111)
				uni.navigateTo({
					url:`/pages/webview/webview?url=${url}`
				})
			},


			/**
			 * 获取评论
			 * @param {Object} obj
			 */
			get_comment(obj) {
				this.$get("~/api/comment/get_list?", {
					source_table: "lost_and_found_square",
					source_field: "lost_and_found_square_id",
					source_id: obj["lost_and_found_square_id"]
				}, (res) => {
					if (res.result && res.result.list) {
						var list = res.result.list;
						this.list_comment = list;
						console.log("评论请求结果：" ,list);
					}
					else if (res.error){
						console.error(res.error);
					}
				});
			},


			/**
			 * 获取对象之后
			 * @param {Object} json 结果对象
			 */
			get_obj_after(json) {
				// 判断是否获取到数据
				if (this.obj) {
					var obj = this.obj;
					// 获取评论
					this.get_comment(obj);
				}
			},

				/**
			 * 获取注册用户用户列表
			 */
			async get_list_user_publisher() {
				var json = await this.$get("~/api/user/get_list?user_group=注册用户");
				if(json.result && json.result.list){
					this.list_user_publisher = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_publisher(id){
				let obj = this.list_user_publisher;
				let ret = "";
				for(let i=0;i<obj.length;i++){
					if(obj[i].user_id==id){
						ret = obj[i].nickname+"-"+obj[i].username;
					}
				}
				return ret;
			},
												},
		created() {
				this.get_list_user_publisher();
												},
		mounted() {
		},
	}

</script>

<style>
	.page_goods {
		padding-bottom: 3rem;
	}

	.link {
		text-align: center;
		padding: 0.5rem 0;
		border: 1px solid #DBDBDB;
		border-radius: 0.5rem;
	}

	.buy_wrap {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
	}

	.dialog {
		width: 100%;
		height: 100vh;
		background: rgba(0, 0, 0, 0.5);
		z-index: 999;
		position: fixed;
		left: 0;
		top: 0;
		padding: 20vh 0;
	}

	.dialog-content {
		width: 500upx;
		height: 60vh;
		margin: 0 auto;
		background: #FFF;
		border-radius: 10upx;
		overflow: hidden;
		position: relative;
		display: flex;
		flex-direction: column;
		padding:  5vh;
		text-align: center;

	}
	.dialog-content span{
		margin-top:  3vh;
		}

	.dialog-close {
		width: 40upx;
		height: 40upx;
		border-radius: 20upx;
		position: absolute;
		right: 10upx;
		top: 10upx;
		font-size: 26upx;
		line-height: 40upx;
		text-align: center;
	}

	.dialog-list {
		padding: 40upx 20upx;
	}

	scroll-view {
		width: 100%;
		height: 100%;
	}

	.show-dialog {
		animation: 100ms showDialog linear forwards;
	}

	.hide-dialog {
		animation: 100ms hideDialog linear forwards;
	}

	@keyframes hideDialog {
		0% {
			opacity: 1;
		}


		100% {
			opacity: 0;
		}
	}

	@keyframes showDialog {
		0% {
			opacity: 0;
		}


		100% {
			opacity: 1;
		}
	}

	/* new style start */
	.diy_detail--new{
		padding: 5px;
	}
	.diy_detail--new .view {
		border-bottom: 1px solid #ccc;
		padding: 8px 0 ;
	}
	.diy_detail--new .view uni-view{
		display: inline-block;
	}
	.diy_detail--new .view .diy_title{
		color: var(--color_primary);
		margin-right: 5px;
		vertical-align: top;
	}
	.diy_detail--new .view .diy_img img{
		width: 100%;
	}
	.diy_detail--new .view .diy_music{
		vertical-align: text-top;
	}
	.diy_detail--new .bar_title{
		position: relative;
		background-color: #fff;
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 2px;
		background: var(--color_primary);
		border-radius: 10px;
		height: 46px;
	}
	.diy_detail--new .bar_title .title{
		margin: 0 auto;
		color: var(--color_white);
		font-weight: 600;
	}
	.diy_detail--new .container-fluid-comment--new{
		margin: 10px 0.6rem;
	}
	.diy_detail--new .container-fluid-comment--new .col{
		display: flex;
		flex: auto;
		flex-wrap: wrap;
		flex-direction: row;
		margin-left: -10px;
	}
	.diy_detail--new .container-fluid-comment--new .btn{
		display: inline-block;
		border: none;
		background: var(--color_primary);
		color: #fff;
		font-size: 16px !important;
		padding: 2px 12px !important;
		border-radius: 20px;
		line-height: inherit;
		margin-bottom: 5px;
		margin-left: 10px;
		margin-right: 0;
	}
	.diy_detail--new .container-fluid-comment--new .btn:after{
		display: none;
	}
	.diy_detail--new .container-fluid-comment--new .btn .uni-icons{
		color: #fff !important;
	}
	.diy_detail--new .comment_title{
		margin-top: 10px;
	}
	/* new style end */

</style>
