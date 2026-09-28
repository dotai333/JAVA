<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <jsp:include page="/layout/head.jsp" />

</head>

<body>

    <!-- SIDEBAR -->
    <jsp:include page="/layout/sidebar.jsp" />

    <!-- NỘI DUNG CHÍNH -->
    <div id="content">

        <!-- NAVBAR -->
        <jsp:include page="/layout/navbar.jsp" />

        <!-- DASHBOARD -->
        <div class="main-body container-fluid p-4">

            <jsp:include page="/view/dashboard.jsp" />

        </div>

        <!-- FOOTER -->
        <jsp:include page="/layout/footer.jsp" />

    </div>

</body>

</html>