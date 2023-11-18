<script>
	import PostForm from "../../../components/PostForm.svelte";
    import {page} from '$app/stores';
    let component;
    const urlParams = new URLSearchParams($page.url.search);
    const postid = urlParams.get('postid');
    let props = {ppid:postid};

</script>
<section>
    <svelte:component this={PostForm} {...props}/>
</section>