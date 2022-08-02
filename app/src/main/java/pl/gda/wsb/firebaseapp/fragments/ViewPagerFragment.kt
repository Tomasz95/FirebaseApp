package pl.gda.wsb.firebaseapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import pl.gda.wsb.firebaseapp.base.ui.BaseFragment
import pl.gda.wsb.firebaseapp.databinding.FragmentViewPagerBinding
import pl.gda.wsb.firebaseapp.fragments.home.HomeFragment
import pl.gda.wsb.firebaseapp.fragments.task.TaskFragment


class ViewPagerFragment :
    BaseFragment<FragmentViewPagerBinding, ViewPagerViewModel>(FragmentViewPagerBinding::inflate) {
    override val vm: ViewPagerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initTabs()

    }

    private fun initTabs() {
        TabLayoutMediator(
            layout.tabLayout, layout.viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "Tasks"
            }
        }.attach()
        layout.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun initViewPager() {
        val fragmentList = arrayListOf<Fragment>(
            HomeFragment(),
            TaskFragment()
        )
        val adapter = ViewPagerAdapter(fragmentList,childFragmentManager,lifecycle)
        layout.viewPager.adapter = adapter
    }
}
