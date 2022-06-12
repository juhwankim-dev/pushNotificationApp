package com.juhwan.anyang_yi.present.utils

class ShortException: Exception("최소 2글자 이상 입력해주세요.")
class ExceedException: Exception("키워드는 10개까지 등록 가능합니다.")
class OutOfMatchException: Exception("한글과 숫자만 등록 가능합니다.")
class RegisteredException: Exception("이미 등록된 키워드입니다.")