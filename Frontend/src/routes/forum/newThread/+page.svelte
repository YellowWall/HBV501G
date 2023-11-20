<script>
	import PostForm from "../../../components/PostForm.svelte";

    let component;
    
    let props = {ppid:0};

</script>
<section>
    <svelte:component this={PostForm} {...props}/>
</section>