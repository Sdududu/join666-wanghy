package com.summary.service.Impl;

import com.summary.dao.ExamDao;
import com.summary.dao.PageDao;
import com.summary.entity.*;
import com.summary.service.StudentService;

    public class StudentServiceImpl implements StudentService {

        //todo 异常处理
        //试卷页面渲染
        @Override
        public Page<Exam> getStuExamPage(int pageNum , int pageSize, int id) {
            PageDao pagedao = new PageDao();
            int totalSize = getStuExamPageNum(id);//数据总数
            int starIndex = (pageNum - 1) * pageSize;
            Page<Exam> page = new Page(pageNum, pageSize, totalSize);//范围限定
            page.setList(pagedao.getStuExamPage(starIndex, pageSize,id));//获得结果
            return page;
        }

        @Override
        public int getStuExamPageNum(int id) {
            PageDao pageDao = new PageDao();
            int total = pageDao.getStuExamPageNum(id);
            return total;
        }

        //选择题渲染
        @Override
        public Page<QuesOption> getStuOptionPage(int pageNum, int pageSize, int exam_id) {
            PageDao pagedao = new PageDao();
            int totalSize = getStuOptionPageNum(exam_id);//数据总数
            int starIndex = (pageNum - 1) * pageSize;
            Page<QuesOption> page = new Page(pageNum, pageSize, totalSize);//范围限定
            page.setList(pagedao.getStuOptionPage(starIndex, pageSize,exam_id));//获得结果
            return page;
        }

        @Override
        public int getStuOptionPageNum(int exam_id) {
            PageDao pageDao = new PageDao();
            int total = pageDao.getStuOptionPageNum(exam_id);
            return total;
        }


        //填空题渲染
        @Override
        public Page<QuesFill> getStuFillPage(int pageNum, int pageSize, int exam_id) {
            PageDao pagedao = new PageDao();
            int totalSize = getStuFillPageNum(exam_id);//数据总数
            int starIndex = (pageNum - 1) * pageSize;
            Page<QuesFill> page = new Page(pageNum, pageSize, totalSize);//范围限定
            page.setList(pagedao.getStuFillPage(starIndex, pageSize,exam_id));//获得结果
            return page;
        }

        @Override
        public int getStuFillPageNum(int exam_id) {
            PageDao pageDao = new PageDao();
            int total = pageDao.getStuFillPageNum(exam_id);
            return total;
        }

        //问答题渲染
        @Override
        public Page<QuesEssay> getStuEssayPage(int pageNum, int pageSize, int exam_id) {
            PageDao pagedao = new PageDao();
            int totalSize = getStuEssayPageNum(exam_id);//数据总数
            int starIndex = (pageNum - 1) * pageSize;
            Page<QuesEssay> page = new Page(pageNum, pageSize, totalSize);//范围限定
            page.setList(pagedao.getStuEssayPage(starIndex, pageSize,exam_id));//获得结果
            return page;
        }

        @Override
        public int getStuEssayPageNum(int exam_id) {
            PageDao pageDao = new PageDao();
            int total = pageDao.getStuEssayPageNum(exam_id);
            return total;
        }


        //我的选择渲染
        @Override
        public Page<QuesOption> getCheckMyOptionAnswerPage(int pageNum, int pageSize, int stu_id,int exam_id) {
            PageDao pagedao = new PageDao();
            int totalSize = getCheckMyOptionAnswerPageNum(stu_id ,exam_id);//数据总数
            int starIndex = (pageNum - 1) * pageSize;
            Page<QuesOption> page = new Page(pageNum, pageSize, totalSize);//范围限定
            page.setList(pagedao.getCheckMyOptionAnswerPage(starIndex, pageSize,stu_id,exam_id));//获得结果
            return page;
        }

        @Override
        public int getCheckMyOptionAnswerPageNum(int stu_id ,int exam_id) {
            PageDao pageDao = new PageDao();
            int total = pageDao.getCheckMyOptionAnswerPageNum(stu_id ,exam_id);
            return total;
        }

        //我的填空题渲染
        @Override
        public Page<QuesFill> getCheckMyFillAnswerPage(int pageNum, int pageSize, int stu_id,int exam_id) {
            PageDao pagedao = new PageDao();
            int totalSize = getCheckMyFillPageNum(stu_id ,exam_id);//数据总数
            int starIndex = (pageNum - 1) * pageSize;
            Page<QuesFill> page = new Page(pageNum, pageSize, totalSize);//范围限定
            page.setList(pagedao.getCheckMyFillAnswerPage(starIndex, pageSize,stu_id,exam_id));//获得结果
            return page;
        }

        @Override
        public int getCheckMyFillPageNum(int stu_id ,int exam_id) {
            PageDao pageDao = new PageDao();
            int total = pageDao.getCheckMyFillAnswerPageNum(stu_id ,exam_id);
            return total;
        }

        //我的问答题答题渲染
        @Override
        public Page<QuesEssay> getCheckMyEssayAnswerPage(int pageNum, int pageSize, int stu_id,int exam_id) {
            PageDao pagedao = new PageDao();
            int totalSize = getCheckMyEssayAnswerPageNum(stu_id,exam_id);//数据总数
            int starIndex = (pageNum - 1) * pageSize;
            Page<QuesEssay> page = new Page(pageNum, pageSize, totalSize);//范围限定
            page.setList(pagedao.getCheckMyEssayAnswerPage(starIndex, pageSize,stu_id,exam_id));//获得结果
            return page;
        }

        @Override
        public int getCheckMyEssayAnswerPageNum(int stu_id ,int exam_id) {
            PageDao pageDao = new PageDao();
            int total = pageDao.getCheckMyEssayAnswerPageNum(stu_id , exam_id);
            return total;
        }

        //成绩页面渲染
        @Override
        public Page<Grade> getStuGradePage(int pageNum , int pageSize, int id) {
            PageDao pageDao = new PageDao();
            int totalSize = getStuGradePageNum(id);//数据总数
            int starIndex = (pageNum - 1) * pageSize;
            Page<Grade> page = new Page(pageNum, pageSize, totalSize);//范围限定
            page.setList(pageDao.getStuGradePage(starIndex, pageSize,id));//获得结果
            return page;
        }

        @Override
        public int getStuGradePageNum(int id) {
            PageDao pagedao = new PageDao();
            int total = pagedao.getStuGradePageNum(id);
            return total;
        }

        //选择题提交
        public int addMyOption(QuesOption optionList,int stu_id,int isright){
            ExamDao examDao = new ExamDao();
            int flag = examDao.addMyOption(optionList,stu_id,isright);
            return flag;
        }


        //填空题提交
        public int addMyFill(QuesFill fillList,int stu_id,int fill_right){
            ExamDao examDao = new ExamDao();
            int flag = examDao.addMyFill(fillList,stu_id,fill_right);
            return flag;
        }
//        public int isRightOption(QuesOption optionList){
//            ExamDao examDao = new ExamDao();
//            int flag = examDao.isRightOption(optionList);
//            return flag;
//
//        }



    }
