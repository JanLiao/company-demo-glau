<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta content="text/html;charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta http-equiv ="Pragma" content = "no-cache"/>
	<meta http-equiv="Cache-Control" content="no cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="format-detection" content="telephone=no"/>
	<script src="https://cdn.bootcss.com/pace/1.0.2/pace.min.js"></script>
	<link href="https://cdn.bootcss.com/pace/1.0.2/themes/pink/pace-theme-flash.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../../plugins/layui/css/layui.css" media="all"/>
	<link rel="stylesheet" type="text/css" href="../../css/fs.css" media="all"/>
	<script type="text/javascript" src="../../plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
	<script type="text/javascript" src="../../plugins/echarts/echarts.min.js"></script>
</head>
<body>
<div class="layui-fluid">
	<div class="layui-row layui-col-space10">
		<div class="layui-col-md12">
			<blockquote class="layui-elem-quote">
				<span style="color: #FF5722;">fsLayui</span>是一个基于layui的快速开发插件，支持数据表格增删改查操作，提供通用的组件，通过配置html实现数据请求，减少前端js重复开发的工作。目前支持单数据表格、多数据表格、tab数据表格、树+数据表格、联动数据表格、layeidt编辑器、下拉框联动等。
			</blockquote>
			<blockquote class="layui-elem-quote layui-quote-nm">
				<p>感兴趣的可以关注一下，最好先清理缓存在访问。<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=ec423b1a69e63af26c66e3756dbeaefd0f7d1ef793d4d8321ec9ef7cf74008f8"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="fsLayuiPlugin交流" title="fsLayuiPlugin交流"></a></p>
				<a href="https://github.com/fallsea/fsLayuiPlugin" target="-bark"><span class="layui-badge layui-bg-black">GitHub下载</span></a>
				<a href="https://gitee.com/fallsea/fsLayuiPlugin" target="-bark"><span class="layui-badge">码云下载</span></a>
				<a href="http://fly.layui.com/case/u/1154664" target="-bark"><span class="layui-badge layui-bg-green">我要点赞</span></a>
				<a href="http://www.itcto.cn/docs/fslayui" target="-bark"><span class="layui-badge layui-bg-red">api文档地址</span></a>
			</blockquote>
		</div>

		<div class="layui-col-md6 layui-col-space5">
			<blockquote class="layui-elem-quote">
				版本更新记录
			</blockquote>
			<div style="height:600px;overflow: auto;">
				<ul class="layui-timeline">
					<li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.8.0.bate</h2>
			            <span class="layui-badge-rim">2018-3-17</span>
			          </div>
			          <ul>
			            <li>新增单选框使用数据字典加载数据</li>
			            <li>新增复选框使用数据字典加载数据</li>
									<li>新增自定义ajax请求类型</li>
									<li>优化树循环加载bug</li>
									<li>新增ajax执行成功错误码统一配置</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.7.1</h2>
			            <span class="layui-badge-rim">2018-2-2</span>
			          </div>
			          <ul>
			            <li>修复table中指定分页数量（pageSize）无效bug</li>
			            <li>修复弹出确认提示框无法关闭bug</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.7.0</h2>
			            <span class="layui-badge-rim">2018-1-27</span>
			          </div>
			          <ul>
			            <li>优化菜单一级菜单默认选中（如果未配置defaultSelectTopMenuId，默认选中第一个）</li>
			            <li>button关键字修改，由buttion改为button（需要注意，会导致不兼容情况）</li>
			            <li>优化配置前端缓存后，获取不到数据，提示问题</li>
			            <li>修复tab数据表格“加载中提示”位置不正确bug</li>
			            <li>修复数据表格右边固定导航菜单显示不正常bug</li>
			            <li>修复多数据表格异步排序错误bug</li>
			            <li>树操作进行大量优化（同一页面支持多个树，复选框，右键菜单、点击回调等）</li>
			            <li>优化form表单使用自定义按钮事件bug</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.6.4</h2>
			            <span class="layui-badge-rim">2018-1-12</span>
			          </div>
			          <ul>
			            <li>优化layedit编辑器不能正常赋值bug</li>
			            <li>新增自定义按钮事件回调处理</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.6.3</h2>
			            <span class="layui-badge-rim">2018-1-3</span>
			          </div>
			          <ul>
			            <li>新增菜单栏配置(支持配置本地菜单和异步加载菜单)</li>
			            <li>优化tab导航菜单</li>
			            <li>优化默认图标，使用font-awesome图标库</li>
			            <li>新增echarts图表案例</li>
			            <li>优化ajax请求异步bug</li>
			            <li>优化附件上传静态文件相对路径</li>
			            <li>新增单页面禁用输入，<a target="_top" href="../../index.html#staticDatagrid">点击体验</a></li>
			            <li>升级layui2.2.5版本</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.6.2</h2>
			            <span class="layui-badge-rim">2017-12-29</span>
			          </div>
			          <ul>
			            <li>新增导航栏右键菜单</li>
			            <li>新增数据表格异步排序(搜索排序，当前页排序)，<a target="_top" href="../../index.html#datagrid">点击体验</a></li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.6.1</h2>
			            <span class="layui-badge-rim">2017-12-26</span>
			          </div>
			          <ul>
			            <li>优化页面相对路径</li>
			            <li>新增菜单栏隐藏/显示</li>
			            <li>新增菜单导航栏路由功能</li>
			            <li>优化导航栏切换后，选中的样式</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.6.0</h2>
			            <span class="layui-badge-rim">2017-12-25</span>
			          </div>
			          <ul>
			            <li>新增tab导航栏（导航栏切换）</li>
			            <li>优化数据字典解析0失败bug</li>
			            <li>优化数据字典table解析，字典值必须配置和字段一致bug</li>
			            <li>新增pace.js进度条插件</li>
			            <li>优化弹出窗口大小超出浏览器窗口大小问题</li>
			            <li>新增404页面</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.5.1</h2>
			            <span class="layui-badge-rim">2017-12-22</span>
			          </div>
			          <ul>
			            <li>优化数据字典解析int类型失败bug</li>
			            <li>优化table.js使用官方调用不加载数据bug</li>
			            <li>优化表格默认样式</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.5.0</h2>
			            <span class="layui-badge-rim">2017-12-20</span>
			          </div>
			          <ul>
			            <li>新增静态表格操作，表单和表格数据提交<a target="_top" href="../../index.html#staticDatagrid">表格数据提交</a></li>
			            <li>单页面操作，topMode 新增new统一改为add</li>
			            <li>优化表格默认样式</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.4.3</h2>
			            <span class="layui-badge-rim">2017-12-18</span>
			          </div>
			          <ul>
			            <li>优化弹出窗口，新增最大化配置</li>
			            <li>优化数据表格排序bug</li>
			            <li>新增数据表格复杂表头支持<a target="_top" href="../../index.html#complexDatagrid">复杂数据表格</a></li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.4.2</h2>
			            <span class="layui-badge-rim">2017-12-15</span>
			          </div>
			          <ul>
			            <li>优化默认配置bug</li>
			            <li>优化编辑、查看获取数据方式，新增前端缓存读取</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.4.1</h2>
			            <span class="layui-badge-rim">2017-12-14</span>
			          </div>
			          <ul>
			            <li>优化动态数据字典入参配置(支持配置传入指定选择器内容)</li>
			            <li>优化新增、修改、查看页面可以共用一个，demo地址：<a target="_top" href="../../index.html#datagrid2">数据表格2</a>，<a target="_brank" href="http://www.itcto.cn/layui/fsLayuiPlugin%E5%8D%95%E9%A1%B5%E9%9D%A2%E6%93%8D%E4%BD%9C/">文档参考</a></li>
			            <li>优化表格自动刷新和文件上传功能，不再强制要求配置<span style="color: red;">mate</span>标签</li>
			            <li>取消<span style="color: red;">lodash.js 和 jquery.formautofill.js</span>的依赖，不需要再引入</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.4.0</h2>
			            <span class="layui-badge-rim">2017-12-12</span>
			          </div>
			          <ul>
			            <li>优化数据字典使用方式(数据字典可以直接配置表格数据转义和下拉框数据填充使用)</li>
			            <li>新增数据表格转义样式和多个值转义</li>
			            <li>避免基础插件和其他的冲突，common.js改为fsCommon.js</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.3.1</h2>
			            <span class="layui-badge-rim">2017-12-10</span>
			          </div>
			          <ul>
			            <li>优化数据表格批量删除提交bug</li>
			            <li>优化数据表格转义功能(新增本地数据字典配置)</li>
			            <li>优化下拉框数据方式(新增本地数据字典配置)</li>
			            <li>新增默认的分页数量和分页下拉选择项配置(fsConfig.js中配置)</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.3.0</h2>
			            <span class="layui-badge-rim">2017-12-8</span>
			          </div>
			          <ul>
			            <li>升级layui2.2.45版本</li>
			            <li>优化联动下拉框编辑页bug</li>
			            <li>新增数据表格内容异步转义</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.2.0</h2>
			            <span class="layui-badge-rim">2017-12-7</span>
			          </div>
			          <ul>
			            <li>新增form表单联动表格操作</li>
			            <li>新增layedit编辑器</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.1.1</h2>
			            <span class="layui-badge-rim">2017-12-4</span>
			          </div>
			          <ul>
			            <li>优化表格首次渲染加载提示</li>
			            <li>树+表格优化</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.1.0</h2>
			            <span class="layui-badge-rim">2017-11-28</span>
			          </div>
			          <ul>
			            <li>新增联动表格支持(简单或复杂的操作)</li>
			            <li>表格新增isLoad是否自动渲染配置</li>
			            <li>升级layui到2.2.3版本</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.0.5</h2>
			            <span class="layui-badge-rim">2017-11-21</span>
			          </div>
			          <ul>
			            <li>优化datagird获取查询条件问题</li>
			            <li>新增tab数据表格案例</li>
			            <li>新增复杂数据表格案例</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.0.4</h2>
			            <span class="layui-badge-rim">2017-11-17</span>
			          </div>
			          <ul>
			            <li>升级layui到2.2.2版本</li>
			          </ul>
			        </div>
			      </li>
			      <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.0.3</h2>
			            <span class="layui-badge-rim">2017-11-17</span>
			          </div>
			          <ul>
			            <li>升级layui到2.2.1版本</li>
			            <li>新增附件上传功能</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.0.2</h2>
			            <span class="layui-badge-rim">2017-11-15</span>
			          </div>
			          <ul>
			            <li>升级layui到2.2.0版本</li>
			            <li>优化数据表格分页查询无数据分页显示问题</li>
			            <li>优化form表单日期控件必须输入id问题</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.0.1</h2>
			            <span class="layui-badge-rim">2017-11-14</span>
			          </div>
			          <ul>
			            <li>优化form表单demo</li>
			            <li>完善datagrid demo</li>
			            <li>优化form.js复选框获取数据问题(layui官网默认提交不同的属性，改成提交统一字段，逗号分隔数据)</li>
			          </ul>
			        </div>
			      </li>
				  <li class="layui-timeline-item">
			        <i class="layui-icon layui-timeline-axis"></i>
			        <div class="layui-timeline-content layui-text">
			          <div class="layui-timeline-title">
			            <h2>1.0.0</h2>
			            <span class="layui-badge-rim">2017-11-12</span>
			          </div>
			          <ul>
			            <li>支持数据表格刷新，查询，修改，删除功能</li>
			            <li>支持树+数据表格操作</li>
			            <li>支持表单操作</li>
			            <li>支持多个数据表格</li>
			          </ul>
			        </div>
			      </li>
				</ul>
			</div>
		</div>
		<div class="layui-col-md6 layui-col-space5">
			<blockquote class="layui-elem-quote">
				图表
			</blockquote>
			<div id="main" style="width: 100%;height:400px;"></div>
	</div>
</div>
<script type="text/javascript" src="../../scripts/home/index.js"></script>
</body>
</html>
