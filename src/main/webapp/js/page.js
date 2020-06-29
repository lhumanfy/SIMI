function pageChange(form,num){
    form.pageIndex.value = num;
    console.log(num)
    form.submit();
}