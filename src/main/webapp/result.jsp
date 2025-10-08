<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .results-sidebar {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .results-sidebar h3 {
            margin-top: 0;
            color: #333;
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
        }
        .results-container {
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #333;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .hit-true {
            color: #28a745;
            font-weight: bold;
        }
        .hit-false {
            color: #dc3545;
            font-weight: bold;
        }
        a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="results-sidebar">
        <h3>Results</h3>
        <div class="results-container">
            <table id="resultsTable">
                <thead>
                <tr>
                    <th scope="col">X</th>
                    <th scope="col">Y</th>
                    <th scope="col">R</th>
                    <th scope="col">Result</th>
                    <th scope="col">Run time</th>
                    <th scope="col">Local time</th>
                </tr>
                </thead>
                <tbody>
                    <!-- Данные загружаются из бина ResultsDAO -->
                    <c:forEach var="result" items="${resultsDAO.results}">
                        <tr>
                            <td>${result.x}</td>
                            <td>${result.y}</td>
                            <td>${result.r}</td>
                            <td class="${result.hit == 1 ? 'hit-true' : 'hit-false'}">
                                ${result.hit == 1 ? 'Hit' : 'Miss'}
                            </td>
                            <td>${result.executionTime} ns</td>
                            <td>${result.timestamp}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <a href='index.jsp'>Back to Main Page</a>
    </div>
</body>
</html>