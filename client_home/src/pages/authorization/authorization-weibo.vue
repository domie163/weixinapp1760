<template>
	<view class="aut-box">
		<view class="aut-h">
			<view class="img-box">
				<view class="img will">

				</view>
				<view class="img handoff">

				</view>
				<view class="img arrive">

				</view>
			</view>
			<view class="texts">
				<text>{{accname}}:</text>
				<text class="text-name">{{Account}}</text>
			</view>
		</view>
		<view class="conter-box">
			<view class="text-bl text-title">
				<text class="text-itme">{{titem}}</text>
			</view>
			<view class="text-bl">
				<view class="text-l"><p>获取你的名字和登录名</p></view>
				<view class="text-l"><p>获取你的企业名称</p></view>
				<view class="text-l"><p>获取你的昵称、头像、性别、地区和用户状态</p></view>

			</view>
		</view>
		<view class="btn-n-box">
			<button class="btn de-author" @click="open">确认授权</button>
			<button class="btn temporarily-author"  @click="returns">暂不授权</button>
		</view>

		<uni-popup class="popups" ref="popup" :mask-click="false">
			<view class="centor">
				<view class="centor-c">
					<h2>微博授权登录</h2>
				</view>

				<view class="centor-c">
					<h4>申请获得以下权限</h4>
				</view>

				<view class="centor-c">
					<text>获取你的公开信息 (昵称、投降、地区以及性别)</text>

				</view>

				<view class="btns">
					<button class="btn bt-de-author" @click="close">取消授权</button>
					<button class="btn Confirm-author" @click="aut" :disabled='authorization'>确认授权</button>
				</view>


			</view>

		</uni-popup>
		</view>
</template>

<script>

	export default {

		data() {
			return {
				titem:'',
				accname:'',
				Account:0,
				authorization:false,

			};
		},
		onReady() {},
			methods: {
				open() {
					this.$refs.popup.open('center')
				},
				close() {
					this.$refs.popup.close()
				},
				aut(){
					this.authorization=true
					uni.reLaunch({
						url: '../account/login'
					});
				},
				returns(){
					uni.navigateBack({

					})
				}
			},
			onLoad(option) {
				this.Account = option.id;
				console.log(this.Account)
				if(this.Account == 3){
					this.titem = '你同意将以下信息授权给微博';
					this.accname = '微博账号'
				}else{
					uni.navigateBack({

					})
				}
			}

	}
</script>

<style scoped>
	.uni-body uni-page-wrapper{
		background-color: #ebebeb !important;
	}

	.aut-box{
		width: 100%;
	}

	.aut-h{
		width: 100%;
		background-color: #00A4FF;
	}

	.aut-h .texts{
		height: 55px;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.conter-box{
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;

	}

	.popups{
		width: 414rpx;
	}
	.btn{
		width: 80%;
		margin: 0 auto;
	}
	.centor{
		    width: 142%;
		    background-color: #f3f3f3;
		    display: flex;
		    flex-direction: column;
		    box-sizing: border-box;
		    border-radius: 5px;
		    position: relative;
		    right: 20%;
	}
	.centor-c{
		margin: 10px;
	}

	.centor-c text{
		font-size: 12px;
		color: #080808;
	}

	.btns{
		display: flex;
		margin: 5px;
	}


	.img-box{
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.img-box .img{
		width: 65px;
		height: 65px;
		border: 1px solid #bdbdbd;
		margin: 30px 10px 10px 10px;
		border-radius: 50%;

	}
	.handoff{
		border: none !important;
	}
	.img-box .will{
		background: url(../../static/img/weibo-n.png) no-repeat;

	}

	.img-box .handoff{
		background: url(../../static/img/switch.png) no-repeat;
	}
	.img-box .arrive{
		background: url(../../static/img/pier.jpg) no-repeat;
	}



	.text-bl{
		margin: 10px;
	}

	.text-bl .text-itme{
		margin-right: 40px;
		font-weight: 700;
	}

	.text-l{
		font-size: 12px;
		color: #2c2c2c;
		margin-bottom: 5px;
	}
	.text-input{
		width: 100px;
		margin-left: 10px;

	}


	.txet-name{
		color: #323232 !important;
	}

	.btn-n-box{
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: row-reverse;
	}

	.btn-n-box .de-author{
		width: 50%;
		margin: 10px;
		border: none;
		background-color: #323232;
		color: #f1f1f1;
	}
	.btn-n-box .temporarily-author{
		width: 50%;
		margin: 10px;
		border: none;
		background-color: #323232;
		color: #f1f1f1;
	}

	.btns .Confirm-author{
		width: 50%;
		margin: 10px;
		border: none;
		background-color: #323232;
		color: #f1f1f1;
	}

	.btns .bt-de-author{
		width: 50%;
		margin: 10px;
		border: none;
		background-color: #323232;
		color: #f1f1f1;
	}

</style>
