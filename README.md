# Musinsa URL Shortener

## [execute]
How to. $ ./install.sh

## [install]
----
### Create Project Clone <br>
\'$ git clone https://github.com/SunaHwang/musinsa.git\'

----
### DB : MySQL 사용.<br>
1. DB Connection 설정<br>
    - hostname : 127.0.0.1
    - port : 3306
2. create schema <br>
*test 라는 이름의 schema 생성*<br>
\'CREATE SCHEMA `test` ;\'
----

## [execute]
---
### URL
http://localhost:8080<br>
1. 변환 : Enter URL 에 원하는 URL 입력 후 엔터 또는 shortening 버튼 클릭
2. Rediect : 텍스트 박스 아래에 변환된 URL 클릭 혹은 복사 후 주소창에 붙여넣기
----

## [table]
1. test_chars : shorten url을 생성하기 위한 기준 데이터를 저장하는 테이블
2. test_shorteners : 원래의 URL과 변환된 URL을 저장하는 테이블
3. test_logs : 변환 요청에 대한 log를 기록하는 테이블
4. test_redirect_logs : rediect url 사용에 대한 log를 기록하는 테이블

----
## [test case]<br>

|title|테스트목적|전제조건|테스트 절차|기대 결과|테스트 결과|
|---|:---:|:---:|:---:|:---:|:---:|
|URL 입력|최소 길이 이상의 URL이 들어오는지 확인|입력 가능한 최소 길이 : 4자 (3.ly 와 같이 가장 짧은 url이 4자리여서, 4자리를 최소 길이로 잡음.)|1. URL 입력 란에 3자리 이하의 URL을 입력<br>2. ‘Shortening’ 버튼 클릭 혹은 입력란에서 Enter키 누름|최소 4글자 이상을 입력해주세요.’ 라는 alert창이 팝업되어야 한다.|OK|
|URL 입력|4~7자 사이의 URL을 입력했을 시, 변환되는 key의 길이 확인.|1. 4이상 7이하 자리수의 URL 변환 시, 4이상 7이하 자리수자의 URL(http://localhost:/ 뒤의 자리 수를 의미) 생성<br>2. 8자 이상의 URL 변환 시, 8자의 URL 생성|1. 4이상 7이하 자리수의 URL 입력 후 변환|1. 4이상 7이하 자리수의 URL은 URL 역시 4이상 7이하 자리수자로 생성<br>2. 8자 이상의 URL은 무조건 8자리의 URL로 변환|OK|
|URL 변환|동일한 URL이 들어올 경우, 같은 값을 반환하는지 확인| 1. http:// 로 시작하는 URL과 https:// 로 시작하는 URL은 동일하다고 가정. 즉, http://www.google.com 과 https://www.google.com 은 동일하다고 가정|1. http://localhost:8080/ 로 시작하는 변환된 문자열을 입력<br>2. 변환된 문자열일 경우, 원래의 URL로 redirect되는지 여부 확인|모두 같은 URL로 변환되어야 한다.|OK|
|URL 확장|변환된 URL이 들어올 경우, 원래 URL로 redirect 되는지 확인|1. http:// 로 redirect|1. http://localhost:8080/ 로 시작하는 변환된 문자열을 입력<br>2. 변환된 문자열일 경우, 원래의 URL로 redirect되는지 여부 확인|1. 해당 URL이 변환된 URL일 경우 http:// + 원래URL 주소로 redirect 되어야 한다.<br>2. 변환된 URL이 아닐 경우, error page로 이동한다.|OK|
|Log 기록|URL 변환 시, log 기록 여부 확인|1. 변환 요청하는 URL에 상관없이, 변환 시 무조건 log 기록| 1. URL 입력 후 변환<br>2. test_logs 테이블에 데이터 확인(조회 화면 없으므로, 테이블 직접 조회)| 1. URL 변환 시, 변환 요청한 URL과 변환 요청한 일시가 기록되어야 한다.|OK|
|Redirect Log 기록|변환된 URL을 주소창에 입력 후 이동 시, 원래의 URL로 redirect되며 log가 기록되는지 여부 확인1. 변환된 URL일 경우, redirect 되며 redirect log 저장<br>2. 비정상적인 URL일 경우, redirect log 저장되지 않음|1. URL 변환 후, 변환된 URL을 클릭하거나, 주소창에 직접 입력<br>2. 존재하지 않는 URL 입력<br>3. test_redirect_logs 테이블에 데이터 확인(조회 화면 없으므로, 테이블 직접 조회)<br>4. redirect log가 이미 있는 경우, 변환된 URL이 같으면 cnt 증가| 1. 제대로 변환된 URL일 경우, 주소창에 입력 시 원래의 URL로 redirect 되며, redirect log가 기록되어야 한다.<br>1-1. 1의 경우 log가 새로 생성(redirect 요청이 최초인 경우)되거나, log 내의 cnt가 증가(기존에 요청했던 적이 있는 경우)해야 한다.<br>2. 제대로 변환된 URL이 아닐 경우, 에러 페이지로 이동해야 한다.|OK|

----
## [배제 사항]
1. cache : LRU cache를 사용하려고 하였으나, @EnableCaching을 사용하여 간단하게만 캐싱.
2. 성능 : 일정 시점 이상에서는, url 생성 시 중복되는 현상 발생. url의 중복을 비교하기 위해 성능 저하될 가능성 있으나, 현재는 배제하고 구현.
