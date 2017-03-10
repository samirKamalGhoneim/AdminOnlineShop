<body class="skin-black">
    <!-- header logo: style can be found in header.less -->
    <header class="header">
        <a href="index.jsp" class="logo">
            <!-- Add the class icon to your logo image or logo icon to add the margining -->
            OnlineShopping
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="navbar-right">
                <ul class="nav navbar-nav">

                    <!-- User Account: style can be found in dropdown.less -->
                    <li>
                        <a >

                            Log Out
                        </a>

                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <div class="wrapper row-offcanvas row-offcanvas-left">
        <!-- Left side column. contains the logo and sidebar -->
        <aside class="left-side sidebar-offcanvas">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- Sidebar user panel -->
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="img/avatar3.png" class="img-circle" alt="User Image" />
                    </div>
                    <div class="pull-left info">
                        <p>Hello, Admin</p>


                    </div>
                </div>
                <!-- search form -->
                <form action="CustomProduct.jsp" method="get" class="sidebar-form">
                    <div class="input-group">
                        <input type="text" name="q" class="form-control" placeholder="Search..."/>
                        <span class="input-group-btn">
                            <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                        </span>
                    </div>
                </form>
                <!-- /.search form -->
                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">
                    <li class="active">
                        <a href="index.jsp">
                            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                        </a>
                    </li>                        
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-laptop"></i>
                            <span>Product</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="AllProduct.jsp"><i class="fa fa-angle-double-right"></i> Show All </a></li>
                            <li><a href="addProduct.jsp"><i class="fa fa-angle-double-right"></i> Add Product</a></li>
                            <li><a href="AddQuantity.jsp"><i class="fa fa-angle-double-right"></i> Add Quantity</a></li>
                        </ul>
                    </li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-laptop"></i>
                            <span>Orders</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="/AdminJsp/UsersHasOrders"><i class="fa fa-angle-double-right"></i> Show All Orders</a></li>
                            <!--<li><a href="addCategory.jsp"><i class="fa fa-angle-double-right"></i> Add category</a></li>-->

                        </ul>
                    </li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-laptop"></i>
                            <span>Users</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="/AdminJsp/GetAllUsers"><i class="fa fa-angle-double-right"></i> Show All Users</a></li>

                        </ul>
                    </li>
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-laptop"></i>
                            <span>Recharge Cards</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="AddRechargeCard.jsp"><i class="fa fa-angle-double-right"></i>Add Recharge Cards</a></li>

                        </ul>
                    </li>

                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>

        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side">
            <section class="content">
                <div class="row">
                    <div class="col-xs-12 connectedSortable">
                        <!-- /.col -->