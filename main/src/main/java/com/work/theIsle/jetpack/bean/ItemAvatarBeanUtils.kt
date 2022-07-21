package com.work.theIsle.jetpack.bean

/**
 * @Author TIKOU
 * @Date 2022/7/21-14:26
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
object ItemAvatarBeanUtils {
    public fun getData(): List<ItemAvatarBean> {
        val list: MutableList<ItemAvatarBean> = mutableListOf()
        val itemAvatarBean1: ItemAvatarBean = ItemAvatarBean(
            "杨幂",
            "雪见",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn20191218ac%2F58%2Fw1024h1434%2F20191218%2Fab20-ikvenfu1521236.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660977153&t=1c52ef4c8f0e6e74929f19feb6558753"
        )

        val itemAvatarBean2: ItemAvatarBean = ItemAvatarBean(
            "刘诗诗",
            "龙葵",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww3.sinaimg.cn%2Fmw690%2Fa5ab7431ly1h4cadu3a7ij21jk2gp7wh.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660977286&t=3710a667d7a0be9bfc4b3449bb2f1f85"
        )

        val itemAvatarBean3: ItemAvatarBean = ItemAvatarBean(
            "胡歌",
            "景天",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fm.cnr.cn%2Fnews%2F20151204%2FW020151204563711934148.jpg&refer=http%3A%2F%2Fm.cnr.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660977323&t=f2f263ed6f84f6412698dd9cf1539d81"
        )

        val itemAvatarBean4: ItemAvatarBean = ItemAvatarBean(
            "霍建华",
            "白豆腐",
            "https://img2.baidu.com/it/u=3792492272,1533636265&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=667"
        )
        val itemAvatarBean5: ItemAvatarBean = ItemAvatarBean(
            "唐嫣",
            "紫萱",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201504%2F19%2F20150419H1047_KchEH.thumb.400_0.png&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660977417&t=33d1c3e367bc21e7e20a6b10cbb13826"
        )
        val itemAvatarBean6: ItemAvatarBean = ItemAvatarBean(
            "刘亦菲",
            "赵灵儿",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201510%2F30%2F20151030191859_nWv4R.png&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660977491&t=1c21fc1628e9e764c8876019c9ffedcd"
        )

        val itemAvatarBean7: ItemAvatarBean = ItemAvatarBean(
            "彭于晏",
            "唐钰小宝",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2F38dbb6fd5266d01609246dc46964c30735fae7cd9f85&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660977528&t=810554f222e51af2b2481ebac86eefb3"
        )

        val itemAvatarBean8: ItemAvatarBean = ItemAvatarBean(
            "阿奴",
            "刘品言",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201507%2F29%2F20150729165431_fFSXW.thumb.700_0.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660977563&t=8ad31527fced669b4a84fddccd476a49"
        )
        list.add(itemAvatarBean1)
        list.add(itemAvatarBean2)
        list.add(itemAvatarBean3)
        list.add(itemAvatarBean4)
        list.add(itemAvatarBean5)
        list.add(itemAvatarBean6)
        list.add(itemAvatarBean7)
        list.add(itemAvatarBean8)
        return list
    }
}