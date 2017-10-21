%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transactions</title>
</head>
<body>
    <table>
        <tr>
            <th>Date</th>
            <th>Receiver</th>
            <th>Sender</th>
            <th>Type</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${transactionlist}" var="TransactionList">
            <tr>
                <td>${TransactionList.get("date")}</td>
                <td>${TransactionList.get("reciver")}</td>
                <td>${TransactionList.get("sender")}</td>
                <td>${TransactionList.get("type")}</td>
                <td>${TransactionList.get("amt")}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>