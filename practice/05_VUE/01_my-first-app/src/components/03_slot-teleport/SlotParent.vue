<template>
  <div>
    <SlotChild>
      <h1>가냐?</h1>
      <img
        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_UafMmRvEgZFR2QvA0MGHZj6jCFlspiEiPg&s"
      />
    </SlotChild>
  </div>
</template>

<script>
import SlotChild from './SlotChild.vue';

export default {
  components: {
    SlotChild,
  },
};
</script>
