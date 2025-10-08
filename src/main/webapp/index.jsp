<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Web lab 2</title>
  <style>
    /* ваш существующий CSS стиль */
    body {
        font-family: 'Arial', sans-serif;
        line-height: 1.6;
        color: #333;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
    }
    .container {
        max-width: 1800px;
        margin: 0 auto;
        padding: 20px;
        border-radius: 8px;
        display: flex;
        gap: 20px;
    }

    .main-content {
        flex: 1;
    }

    .results-sidebar {
        width: 800px;
    }

    header {
        height: 150px;
        width: 200px;
        font-style: italic;
        margin-bottom: 30px;
        padding-bottom: 15px;
        background-color: #f4f4f4;
    }

    input {
        padding-left: 5%, 5%;
    }

    #resultsTable {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    ::selection {
    color: red;
    background: green;
    }

    #resultsTable th, #resultsTable td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: center;
    }

    #resultsTable th {
        background-color: #f2f2f2;
        position: sticky;
        top: 0;
    }

    #resultsTable tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .results-container {
        max-height: 900px;
        overflow-y: auto;
    }

    .button-container {
        display: flex;
        gap: 5px;
    }

    .radio-input {
        display: none;
    }

    .radio-label {
        padding: 8px 8px;
        font-size: 14px;
        border: 1px solid;
        background-color: white;
        border-radius: 5px;
        cursor: pointer;
        transition: all 0.3s ease;
        flex: 1;
        text-align: center;
        min-width: 25px;
    }

    .radio-label:hover {
        background-color: #e6f2ff;
    }

    .radio-input:checked + .radio-label {
        background-color: #007bff;
        color: white;
    }

    .r-radio-label {
        padding: 5px 5px;

    }

    .r-radio-label:hover {
        background-color: #e6f2ff;
    }

    .r-radio-input:checked + .r-radio-label {
        background-color: #007bff;
        color: white;
    }

    .r-radio-input {
        display: none;
    }

    /* Стиль для попадания/непопадания */
    .hit-true {
        color: green;
        font-weight: bold;
    }

    .hit-false {
        color: red;
        font-weight: bold;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="main-content">
    <table>
      <tr>
        <td></td>
        <td>
          <header>
            <p>Pakhomov Daniil</p>
            <p>P3216</p>
            <p>467037</p>
          </header>
        </td>
        <td></td>
      <tr>
        <td></td>
        <td><canvas id="draw" width="840" height="400"></canvas></td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td>
          <label for="X">Choose X coordinate (from -5 to 5):</label>
          <div>
            <input
                    type="text"
                    id="X"
                    name="yCoordinate"/>
          </div>

        </td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td>
          <label>Choose Y Coordinate:</label>
          <div>
            <input type="radio" id="btn-minus4" name="numberGroup" value="-4 " class="radio-input">
            <label for="btn-minus4" class="radio-label">-4&nbsp</label>

            <input type="radio" id="btn-minus3" name="numberGroup" value="-3" class="radio-input">
            <label for="btn-minus3" class="radio-label">-3&nbsp</label>

            <input type="radio" id="btn-minus2" name="numberGroup" value="-2" class="radio-input">
            <label for="btn-minus2" class="radio-label">-2&nbsp</label>

            <input type="radio" id="btn-minus1" name="numberGroup" value="-1" class="radio-input">
            <label for="btn-minus1" class="radio-label">-1&nbsp</label>

            <input type="radio" id="btn0" name="numberGroup" value="0" class="radio-input">
            <label for="btn0" class="radio-label">&nbsp0&nbsp</label>

            <input type="radio" id="btn1" name="numberGroup" value="1" class="radio-input">
            <label for="btn1" class="radio-label">&nbsp1&nbsp</label>

            <input type="radio" id="btn2" name="numberGroup" value="2" class="radio-input">
            <label for="btn2" class="radio-label">&nbsp2&nbsp</label>

            <input type="radio" id="btn3" name="numberGroup" value="3" class="radio-input">
            <label for="btn3" class="radio-label">&nbsp3&nbsp</label>

            <input type="radio" id="btn4" name="numberGroup" value="4" class="radio-input">
            <label for="btn4" class="radio-label">&nbsp4&nbsp</label>
          </div>
        </td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td>
          <legend>Choose R:</legend>
          <input type="checkbox" class="checkbox" id="1" name="R1" value="1">
          <label for="1"> 1</label>
          <input type="checkbox" class="checkbox" id="1.5" name="R1.5" value="1.5">
          <label for="1.5"> 1.5</label>
          <input type="checkbox" class="checkbox" id="2" name="R2" value="2">
          <label for="2"> 2</label>
          <input type="checkbox" class="checkbox" id="2.5" name="R2.5" value="2.5">
          <label for="2.5"> 2.5</label>
          <input type="checkbox" class="checkbox" id="3" name="R3" value="3">
          <label for="3"> 3</label>
        </td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td>
          <input class="styled" type="button" id="button" value="Send dot's coordinates" />
          <p id="demo"></p>
        </td>
        <td></td>
      </tr>
    </table>
  </div>

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
  </div>
</div>
<script src="./script.js"></script>
</body>
</html>