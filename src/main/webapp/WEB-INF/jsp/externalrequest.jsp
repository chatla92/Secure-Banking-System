<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Transactions</title>
</head>
<body>
<table>
    <tr>
        <th>Date</th>
        <th>Receiver</th>
        <th>Type</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${transactionlist}" var="TransactionList">
        <tr>
            <td>${TransactionList.get("id")}</td>
            <td>${TransactionList.get("date")}</td>
            <td>${TransactionList.get("reciver")}</td>
            <td>${TransactionList.get("type")}</td>
            <td>${TransactionList.get("amt")}</td>
            <td><form action="/securebank/transaction" method="post"><button type="submit" id="submit1" name="action" value="transfer " +${TransactionList.get("id")} >Transfer</button></form></td>
            <td><form action="/securebank/transaction" method="post"><button type="submit" id="submit2" name="action" value="decline "+${TransactionList.get("id")}>Decline</button></form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>