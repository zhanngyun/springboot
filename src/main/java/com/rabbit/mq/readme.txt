rabbitMQ的基本操作

    1.rabbitmq管理界面开启
        1.在cmd窗口下进入rabbitmq安装目录下的sbin目录，使用rabbitmq-plugins.bat list查看已安装的插件列表。
        2.使用rabbitmq-plugins.bat enable rabbitmq_management开启网页管理界面
        3.重启rabbitmq。
        4.在浏览器中输入http://127.0.0.1:15672/
        5.输入用户名和密码（默认为guest），使用程序发送一条message给rabbitmq后

    2.新增用户
        1.添加用户 rabbitmqctl add_user username password
        2.添加权限 rabbitmqctl set_permissions -p "/" username ".*" ".*" ".*"
        3.添加administrator角色 rabbitmqctl.bat set_user_tags username administrator
    3.修改用户密码
        rabbitmqctl change_password userName newPassword
    4.删除用户
        rabbitmqctl.bat delete_user username
    5.权限相关命令
        (1) 设置用户权限
            rabbitmqctl  set_permissions  -p  VHostPath  User  ConfP  WriteP  ReadP
        (2) 查看(指定hostpath)所有用户的权限信息
            rabbitmqctl  list_permissions  [-p  VHostPath]
        (3) 查看指定用户的权限信息
            rabbitmqctl  list_user_permissions  User
        (4)  清除用户的权限信息
            rabbitmqctl  clear_permissions  [-p VHostPath]  User
    6.用户角色区分
        (1) 超级管理员(administrator)
            可登陆管理控制台(启用management plugin的情况下)，可查看所有的信息，并且可以对用户，策略(policy)进行操作。
        (2) 监控者(monitoring)
            可登陆管理控制台(启用management plugin的情况下)，同时可以查看rabbitmq节点的相关信息(进程数，内存使用情况，磁盘使用情况等)
        (3) 策略制定者(policymaker)
            可登陆管理控制台(启用management plugin的情况下), 同时可以对policy进行管理。
        (4) 普通管理者(management)
            仅可登陆管理控制台(启用management plugin的情况下)，无法看到节点信息，也无法对策略进行管理。
        (5) 其他的
            无法登陆管理控制台，通常就是普通的生产者和消费者.
    7.重启服务 net stop rabbitMQ && net start rabbitMQs


    8.所有指令表
        add_user        <UserName> <Password>
        delete_user     <UserName>
        change_password <UserName> <NewPassword>
        list_users
        add_vhost    <VHostPath>
        delete_vhost <VHostPath>
        list_vhosts
        set_permissions   [-p <VHostPath>] <UserName> <Regexp> <Regexp> <Regexp>
        clear_permissions [-p <VHostPath>] <UserName>
        list_permissions  [-p <VHostPath>]
        list_user_permissions <UserName>
        list_queues    [-p <VHostPath>] [<QueueInfoItem> ...]
        list_exchanges [-p <VHostPath>] [<ExchangeInfoItem> ...]
        list_bindings  [-p <VHostPath>]
        list_connections [<ConnectionInfoItem> ...]
    9.问题解决,不能使用管理界面
        1.将C:\Users\hspcadmin\.erlang.cookie 复制并替换到 C:\Windows\System32\config\systemprofile路径下
        2.计算机-->管理-->服务和应用程序-->服务-->RabbitMQ-->右键属性-->登录(选中此账户)-->输入当前计算机账户名和密码-->重启服务
    10.maven添加依赖
        <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>4.1.0</version>
        </dependency>

