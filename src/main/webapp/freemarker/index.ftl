<html>
    <title>首页</title>
    <body>
        <table>
            <tr>
                <#list goods as commodity>
                    <td>
                        商品名：${commodity.title}<br>
                        简述：${commodity.summary}<br>
                        详细描述：${commodity.content}<br>
                        价格：${commodity.price} 元<br>
                        <img src="index/image/${commodity.id}" width="200" height="300">
                    </td>
                </#list>
            </tr>
        </table>
    </body>
</html>