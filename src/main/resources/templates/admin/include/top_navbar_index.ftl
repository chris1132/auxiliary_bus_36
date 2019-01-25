<nav role="navigation" class="navbar navbar-static-top">
    <div class="container-fluid">
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">

            <#--对话提醒-->
                <#include "chat_remain.ftl">
            <#--对话提醒 END-->

            <#--提醒-->
                <#include "work_remind.ftl">
            <#--提醒 END-->
            </ul>

        <#--天气咨询-->
           <#include "weather.ftl">
        <#--天气咨询   end-->

        <#--登录账号-->
            <#include "my_account.ftl">
        <#--登录账号 ednd-->

        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>