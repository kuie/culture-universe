<template>
  <div>
    <div id="contactWrite" style="position:relative;">
      <!-- 비회원 입력 폼-->
      <form id="support_write_form" name="writeForm">
        <fieldset>
          <div class="table-w">
            <table>
              <caption id="maintitle"><h3>1:1 고객 문의 게시판</h3></caption>
              <colgroup>
                <col width="85"/>
                <col width="*"/>
                <col width="85"/>
                <col width="*"/>
              </colgroup>
              <tbody>
              <tr>
                <th scope="col">
                  <div class="tb-l">문의유형 *</div>
                </th>
                <td colspan="3">
                  <div class="tb-l">
                    <select name="type" id="type" v-model="type">
<!--                       v-model="types"-->
                      <option value="" selected disabled>문의 유형을 선택해주세요.</option>
                      <option value="환불문의">환불문의</option>
                      <option  value="공연문의">공연문의</option>
                      <option  value="기타">기타</option>
<!--                      <option v-for="(qType,key) in types"-->
<!--                              v-bind:value="qType" :key="key"-->
<!--                              v-model="types">-->
<!--                        {{qType}}</option>-->
                    </select>
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="col">
                  <div class="tb-l">제목 *</div>
                </th>
                <td colspan="3">
                  <div class="tb-l">
                    <input type="text" name="title" id="title" value="" class="txt-input"
                           v-model="title"
                           style="width: 405px"/>
                  </div>
                </td>
              </tr>
              <tr>
                <th scope="col">
                  <div class="tb-l">내용 *</div>
                </th>
                <td colspan="3">
                  <div class="tb-l">
                    <p>평일 업무시간 : AM 09:30 ~ PM 18:00 (주말 , 공휴일 휴무)</p>
                    <font color=blue><b>* 정확하고 빠른 문의파악을 위해 문의사항과 관련된 필수입력정보를 확인하여<br/>내용 기재해 주시기 바랍니다.<br/></b></font>
                    <textarea name="content" id="content" cols="70" rows="10"
                              v-model="content"></textarea>
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="4">
                  <div class="agree">
                    <div class="tb-l">
                      <font color=red>"*" 필수항목입니다.</font>
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="4">
                  <div class="agree" >
                    <div class="tb-l" style='text-align:center;padding:10px;font-size:15px;'>
                      <h6><b>2018년 10월 18일 부터 산업안전보건법에 의거 고객응대근로자 보호 조치가 시행되었습니다.<br/>
                        고객만족을 위한 응대를 준비하고 있으니, <br/>
                        폭언이나 욕설은 삼가해 주시고, 따뜻한 마음으로 문의 접수 부탁드립니다.</b></h6>
                    </div>
                    <button type="button" class="btn btn-default pull-right" id="button" @click="postData">
                      문의등록
                    </button>
                  </div>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </fieldset>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "qna",
  props: {
  },
  data(){
    return {
      type: '' ,
      // types: {qRefund:'환불문의', qPerform:'공연문의',qEtc:'기타'},
      title: '',
      content: ''
    }
  },
  methods:{
    onChange(event) {
      console.log(event.target.value)
    },
    postData:function (){
      console.log("데이터 실행 확인")
      axios({
        url: '/api/qna/register',
        method: 'post',
        headers: {
          "Content-Type": 'application/json'
        },
        data: {
          type: this.type,
          title: this.title,
          content: this.content,
        },
        dataType: 'json',
      }).then(res => {
        console.log(res);
        alert('문의가 등록되었습니다.')
        this.$router.push({name: "qna"})
      }).catch(err => {
        console.log(err);
      });
    }
  }
}
</script>

<style scoped>
#contactWrite {
  width: 900px;
  margin-left: 20%;
  /*min-height: 675px;*/
  /*_height: 675px;*/
}

/*테이블 테두리*/
#contactWrite th,
#contactWrite td {
  border: 1px solid #d6e2e9;
}

/*section*/
#contactWrite th {
  padding: 10px 0 5px;
  font-size: 16px;
  font-weight: bold;
  color: #414141;
  background-color: #f6f9fa;
}

#contactWrite th .tb-l {
  padding-left: 12px;
  letter-spacing: -1px;
}

#contactWrite td {
  padding: 8px 0 7px;
  font-size: 14px;
  color: #6b6b6b;
  background-color: #fff;
}

#contactWrite td .tb-l {
  position: relative;
  padding: 0 9px;
}

#contactWrite td .tb-l .btn-logout {
  position: absolute;
  right: 10px;
  top: -1px;
}

#contactWrite td .txt-input,
#contactWrite td textarea {
  font-size: 12px;
}

#contactWrite td .txt-input {
  height: 24px;
  line-height: 24px;
  border: 1px solid #c9d0d6;
}

#contactWrite td textarea {
  overflow-y: scroll;
  width: 490px;
  max-width: 100%;
  max-height: 180px;
  height: 180px;
  border: 1px solid #c9d0d6;
}

#contactWrite td .html {
  color: #7f9db9;
}

#contactWrite td .html-red {
  color: #ff0000;
}

#contactWrite .file-w {
  overflow: hidden;
  position: relative;
  display: inline-block;
  width: 335px;
  height: 26px;
  padding-bottom: 1px;
  font-size: 0;
  line-height: 0;
  /*background: url(./img/btn/h26_w78_addfile.gif) no-repeat right 0;*/
}

#contactWrite .file-w .ftrick {
  cursor: pointer;
  position: absolute;
  right: 0;
  top: 0;
  width: 85px;
  height: 26px;
  background: none;
  border: 0 none;
  opacity: 0;
  filter: alpha(opacity=0);
}

#contactWrite .btn-foot {
  margin-top: 45px;
  font-size: 0;
  line-height: 0;
  text-align: center;
}

#contactWrite .btn-foot .btn-sp {
  margin-left: 11px;
}

#contactWrite .btn-foot .btn-dbh48 * {
  padding-left: 35px;
  padding-right: 45px;
}

#contactWrite .btn-foot .btn-dbh48 .raw-db {
  left: -35px;
}

/* 개인정보 동의 */
#contactWrite h3.box-tit {
  position: relative;
  width: 566px;
  margin: 0 auto;
  height: 38px;
  line-height: 41px;
  padding-left: 20px;
  font-size: 14px;
  color: #333;
  font-weight: bold;
  background: #f6f6f6;
  border: 1px solid #e0e0e0;
  border-bottom: 0;
  text-align: left;
}

#contactWrite .agreement_choice {
  position: absolute;
  top: 0px;
  right: 20px;
  text-align: right;
  font-size: 12px;
  font-weight: normal;
  color: #666;
}

#contactWrite .agreement_choice input {
  width: 12px;
  height: 12px;
  vertical-align: middle;
}

#contactWrite .tbl {
  width: 98%;
  margin: 0 auto;
  border-top: 1px solid #e0e0e0;
  border-left: 1px solid #e0e0e0;
}

#contactWrite .tbl caption {
  display: none;
}

#contactWrite .tbl th,
#contactWrite .tbl td {
  height: 37px;
  padding: 0;
  margin: 0;
  border-right: 1px solid #e0e0e0;
  background: #fff;
  border-bottom: 1px solid #e0e0e0;
  font-size: 12px;
}

#contactWrite .tbl th {
  line-height: 39px;
  background: #fdfdfd;
  text-align: center;
}

#contactWrite .tbl td div {
  padding: 0 5px;
  text-align: left;
  color: #666;
}

#contactWrite .tbl td div ul {
  margin-left: 15px;
}

#contactWrite .tbl td div ul li input {
  width: 12px;
  height: 12px;
  vertical-align: middle;
}

#contactWrite .btm-txt {
  height: 30px;
  margin-left: 10px;
  padding-top: 5px;
  font-size: 11px;
  color: #666;
  text-align: left;
}

/*custom*/
#contactWrite .table-w {
  padding: 10px;
  padding-left: 25%;
  margin-left: auto;
}

#maintitle {
  text-align: center;
  font-weight: bold;
  line-height: 1.15;
  font-size: 2.5rem;
  margin-block-start: 0.83em;
  margin-block-end: 0.83em;
  margin-inline-start: 0.83em;
  margin-inline-end: 0.83em;
  letter-spacing: -0.03em;
}

h6 {
  font-size: small;
}

#button {
  float: right;
}
</style>
